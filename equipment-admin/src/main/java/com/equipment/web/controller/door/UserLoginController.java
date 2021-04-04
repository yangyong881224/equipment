package com.equipment.web.controller.door;

import com.equipment.common.constant.UserConstants;
import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.domain.AjaxResult;
import com.equipment.common.core.domain.entity.SysRole;
import com.equipment.common.core.domain.entity.SysUser;
import com.equipment.common.utils.ShiroUtils;
import com.equipment.common.utils.StringUtils;
import com.equipment.framework.shiro.service.SysPasswordService;
import com.equipment.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

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
        return "redirect:/user/index";
    }

    @PostMapping("/regist")
    @ResponseBody
    public AjaxResult ajaxRegist(SysUser user){
        String sysUser = userService.checkLoginNameUnique(user.getLoginName());
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))){
            return error("注册用户'" + user.getLoginName() + "'失败，账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy("SELF");
        user.setUserType("01");
        return toAjax(userService.insertUser(user));
    }

}
