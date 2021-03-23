package com.equipment.web.controller.door;

import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.domain.AjaxResult;
import com.equipment.common.core.domain.entity.SysRole;
import com.equipment.common.utils.ShiroUtils;
import com.equipment.common.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @UserLoginController:
 * @author: Yayo
 * @date: 2021/3/23 23:20
 */
@RequestMapping("/user")
@Controller
public class UserLoginController extends BaseController {

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e){
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()));
            return error(msg);
        }
    }

    @GetMapping("/logout")
    public String logout(){
        ShiroUtils.logout();
        return "/door/index";
    }


}
