package com.equipment.web.controller.door;

import com.equipment.common.utils.ShiroUtils;
import com.equipment.system.domain.Purchase;
import com.equipment.system.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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

    @GetMapping
    public String purchase(ModelMap mmap){
        return "/door/purchase";
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
    public List<Purchase> list(){
        if(ShiroUtils.getSysUser() == null){
            throw new RuntimeException("用户未登录");
        }else{
            List<Purchase> purchaseList = purchaseService.findByUserId(ShiroUtils.getUserId());
            return null;
        }
    }
}
