package com.equipment.web.controller.door;

import com.equipment.common.core.controller.BaseController;
import com.equipment.common.utils.ShiroUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @IndexController:
 * @author: Yayo
 * @date: 2021/2/21 14:40
 */
@Controller
public class UserIndexController extends BaseController {

    // 系统首页
    @GetMapping("/user/index")
    public String index(ModelMap mmap){
        if(ShiroUtils.getSysUser() != null){
            mmap.put("user", ShiroUtils.getSysUser());
        }
        return "/door/index";
    }
}
