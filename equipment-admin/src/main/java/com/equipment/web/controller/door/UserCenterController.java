package com.equipment.web.controller.door;

import com.equipment.common.constant.UserConstants;
import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.domain.AjaxResult;
import com.equipment.common.core.domain.entity.SysUser;
import com.equipment.common.utils.DateUtils;
import com.equipment.common.utils.ShiroUtils;
import com.equipment.common.utils.StringUtils;
import com.equipment.framework.shiro.service.SysPasswordService;
import com.equipment.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @UserCenterController:
 * @author: Yayo
 * @date: 2021/4/3 12:11
 */
@RequestMapping("/user")
@Controller
public class UserCenterController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @GetMapping("/user_center")
    public String userCenter(ModelMap mmap){
        if(ShiroUtils.getSysUser() != null){
            mmap.put("user",ShiroUtils.getSysUser());
            return "/door/user_center";
        }else{
            return "redirect:/user/index";
        }
    }


    @PutMapping("/user_center/update")
    @ResponseBody
    public AjaxResult updateUser(ModelMap mmap, SysUser user){
        if(ShiroUtils.getSysUser() != null){
            user.setUserId(ShiroUtils.getUserId());
            user.setUpdateTime(new Date());
            user.setUpdateBy(ShiroUtils.getLoginName());
            if (StringUtils.isNotEmpty(user.getPhonenumber())
                    && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
            {
                return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
            }
            else if (StringUtils.isNotEmpty(user.getEmail())
                    && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
            {
                return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
            }
            Integer updateCount = userService.updateUser(user);
            if(updateCount > 0){
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
                mmap.put("user",ShiroUtils.getSysUser());
                return toAjax(1);
            }
        }
        return error("修改失败");

    }


    @GetMapping("/user_password")
    public String userPassword(ModelMap mmap){
        if(ShiroUtils.getSysUser() != null){
            mmap.put("user",ShiroUtils.getSysUser());
            return "/door/user_password";
        }else{
            return "redirect:/user/index";
        }
    }


    @PutMapping("/user_password/update")
    @ResponseBody
    public AjaxResult changePassword(ModelMap mmap, String newPassword, String oldPassword){
        if(ShiroUtils.getSysUser() != null){
            SysUser user = ShiroUtils.getSysUser();
            if (!passwordService.matches(user, oldPassword))
            {
                return error("修改密码失败，旧密码错误");
            }
            if (passwordService.matches(user, newPassword))
            {
                return error("新密码不能与旧密码相同");
            }
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
            user.setPwdUpdateDate(DateUtils.getNowDate());
            if (userService.resetUserPwd(user) > 0)
            {
                ShiroUtils.setSysUser(userService.selectUserById(user.getUserId()));
                mmap.put("user", ShiroUtils.getSysUser());
                return success();
            }
        }
        return error("修改失败");
    }


}
