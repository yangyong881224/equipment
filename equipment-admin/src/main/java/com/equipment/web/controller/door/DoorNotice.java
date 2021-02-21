package com.equipment.web.controller.door;

import com.equipment.system.domain.SysNotice;
import com.equipment.system.service.ISysNoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @DoorNotice:
 * @author: Yayo
 * @date: 2021/2/21 17:19
 */
@Controller
public class DoorNotice {

    @Autowired
    private ISysNoticeService sysNoticeService;

    @PostMapping("/list")
    @ResponseBody
    public List<SysNotice> paging(){
        return null;
    }
}
