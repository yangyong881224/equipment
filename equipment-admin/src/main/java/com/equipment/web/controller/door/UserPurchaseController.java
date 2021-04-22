package com.equipment.web.controller.door;

import com.equipment.common.utils.ShiroUtils;
import com.equipment.system.domain.Equipment;
import com.equipment.system.domain.Purchase;
import com.equipment.system.service.IEquipmentService;
import com.equipment.system.service.IPurchaseService;
import com.equipment.web.controller.vo.UserPurchaseVO;
import com.google.common.collect.Lists;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Boolean create(Long equipmentId){
        if(ShiroUtils.getSysUser() == null){
            throw new RuntimeException("用户未登录");
        }else{
            Purchase purchase = new Purchase();
            purchase.setEquipmentId(equipmentId);
            purchase.setUserId(ShiroUtils.getUserId());
            return purchaseService.create(purchase);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public List<UserPurchaseVO> list(){
        if(ShiroUtils.getSysUser() == null){
            throw new RuntimeException("用户未登录");
        }else{
            List<Purchase> purchaseList = purchaseService.findByUserId(ShiroUtils.getUserId());
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
}
