package com.equipment.web.controller.door;

import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.page.TableDataInfo;
import com.equipment.common.utils.ShiroUtils;
import com.equipment.system.domain.SysNotice;
import com.equipment.system.service.ISysNoticeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @NoticeController:
 * @author: Yayo
 * @date: 2021/3/21 15:37
 */
@RequestMapping("/user/notice")
@Controller
public class UserNoticeController extends BaseController {

    @Autowired
    private ISysNoticeService sysNoticeService;

    @GetMapping
    public String notice(ModelMap mmap){
        if(ShiroUtils.getSysUser()!=null){
            mmap.put("user",ShiroUtils.getSysUser());
        }
        return "/door/news";
    }


    @ResponseBody
    @GetMapping("/paging")
    public List<SysNotice> paging(Integer pageNo, Integer pageSize, String noticeType){
        System.out.println(ShiroUtils.getSysUser());
        PageHelper.startPage(pageNo, pageSize, "create_time desc");
        SysNotice sysNotice = new SysNotice();
        sysNotice.setNoticeType(noticeType);
        sysNotice.setStatus("0");
        List<SysNotice> noticeList = sysNoticeService.selectNoticeList(sysNotice);
        return noticeList;
    }
}
