package com.equipment.web.controller.door;

import com.equipment.common.utils.ShiroUtils;
import com.equipment.system.domain.Borrow;
import com.equipment.system.domain.Equipment;
import com.equipment.system.domain.Purchase;
import com.equipment.system.service.IBorrowService;
import com.equipment.system.service.IEquipmentService;
import com.equipment.system.service.IPurchaseService;
import com.equipment.web.controller.vo.UserPurchaseVO;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @UserPurchaseController:
 * @author: Yayo
 * @date: 2021/4/18 13:45
 */
@Controller
@RequestMapping("/user/purchase")
public class UserPurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IBorrowService borrowService;

    @GetMapping
    public String purchase(ModelMap mmap){
        if(ShiroUtils.getSysUser() == null){
            return "redirect:/user/index";
        }else {

            mmap.put("user",ShiroUtils.getSysUser());
            return "/door/purchase";
        }
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String, String> create(Purchase purchase){
        Map<String, String> result = new HashMap<>();
        if(ShiroUtils.getSysUser() == null){
            result.put("code","-1");
            result.put("msg","用户未登录");
        }else{
            purchase.setUserId(ShiroUtils.getUserId());
            purchaseService.create(purchase);
            result.put("code","0");
            result.put("msg","已加入清单");
        }
        return result;

    }

    @GetMapping("/list")
    @ResponseBody
    public List<UserPurchaseVO> list(){
        if(ShiroUtils.getSysUser() == null){
            throw new RuntimeException("用户未登录");
        }else{
            List<Purchase> purchaseList = purchaseService.findByUserId(ShiroUtils.getUserId());
            if(purchaseList.isEmpty()){
                return new ArrayList<>();
            }
            List<Equipment> equipmentList = equipmentService.selectEquipmentByIds(purchaseList.stream().map(Purchase::getEquipmentId).collect(Collectors.toList()));
            List<UserPurchaseVO> userPurchaseVOList = Lists.newArrayList();
            purchaseList.stream().forEach(purchase -> {
                Equipment equipment = equipmentList.stream().filter(eq -> eq.getId().equals(purchase.getEquipmentId())).findFirst().orElse(null);
                if(equipment != null ){
                    UserPurchaseVO userPurchaseVO = new UserPurchaseVO();
                    BeanUtils.copyProperties(purchase, userPurchaseVO);
                    userPurchaseVO.setEquipmentName(equipment.getName());
                    userPurchaseVO.setPath(equipment.getPath());
                    userPurchaseVO.setQuantity(equipment.getQuantity());
                    userPurchaseVO.setBorrowQuantity(equipment.getBorrowQuantity());
                    userPurchaseVO.setFlag(equipment.getFlag());
                    userPurchaseVOList.add(userPurchaseVO);
                }
            });

            return userPurchaseVOList;
        }
    }

    @DeleteMapping("/remove")
    @ResponseBody
    public Boolean remove(Long id){
        return purchaseService.delete(id);
    }

    @PostMapping("/borrow")
    @ResponseBody
    public Boolean creates(@RequestBody List<UserPurchaseVO> userPurchaseVOList){
        List<Purchase> purchaseList = purchaseService.findByIds(userPurchaseVOList.stream().map(UserPurchaseVO::getId).collect(Collectors.toList()));
        for(Purchase purchase : purchaseList){
            Equipment equipment = equipmentService.selectEquipmentById(purchase.getEquipmentId());
            Borrow borrow = new Borrow();
            borrow.setEquipmentId(purchase.getEquipmentId());
            borrow.setCreatedAt(purchase.getStartTime());
            borrow.setReturnAt(purchase.getEndTime());
            borrow.setEquipmentName(equipment.getName());
            borrow.setBorrowNum(userPurchaseVOList.stream().filter(userPurchaseVO -> userPurchaseVO.getEquipmentId().equals(borrow.getEquipmentId())).findFirst().orElse(new UserPurchaseVO()).getBorrowNum());

            borrow.setFlag(0);
            borrow.setUserId(ShiroUtils.getUserId());
            borrow.setUserName(ShiroUtils.getSysUser().getUserName());
            borrow.setExamineFlag(0);
            borrow.setUrgeReturn(0);
            borrowService.insertBorrow(borrow);
            purchaseService.delete(purchase.getId());
        }
        return true;
    }
}
