package com.equipment.web.controller.door;

import com.equipment.common.utils.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/purchase")
public class UserPurchaseController {

    @GetMapping
    public String purchase(ModelMap mmap){
        if(ShiroUtils.getSysUser() == null){
            return "redirect:/user/index";
        }else{
            mmap.put("user",ShiroUtils.getSysUser());
            return "";
        }
    }
}
