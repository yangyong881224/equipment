package com.equipment.web.controller.door;

import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.page.TableDataInfo;
import com.equipment.common.utils.ShiroUtils;
import com.equipment.system.domain.Borrow;
import com.equipment.system.domain.SysNotice;
import com.equipment.system.enums.BorrowFlagEnum;
import com.equipment.system.service.IBorrowService;
import com.equipment.system.service.ISysNoticeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private IBorrowService borrowService;

    @GetMapping("/{noticeTypeId}")
    public String notice(ModelMap mmap, @PathVariable Integer noticeTypeId){
        if(ShiroUtils.getSysUser()!=null){
            mmap.put("user",ShiroUtils.getSysUser());
        }
        mmap.put("noticeTypeId",noticeTypeId);
        return "/door/news";
    }

    @GetMapping("/detail/{noticeId}")
    public String detail(ModelMap mmap, @PathVariable Long noticeId){
        if(ShiroUtils.getSysUser() != null){
            mmap.put("user",ShiroUtils.getSysUser());
        }
        mmap.put("noticeId", noticeId);
        return "/door/news_detail";
    }


    @ResponseBody
    @GetMapping("/paging")
    public TableDataInfo paging(Integer pageNo, Integer pageSize, String noticeType){
        PageHelper.startPage(pageNo, pageSize, "create_time desc");
        SysNotice sysNotice = new SysNotice();
        sysNotice.setNoticeType(noticeType);
        sysNotice.setStatus("0");
        List<SysNotice> noticeList = sysNoticeService.selectNoticeList(sysNotice);
        return getDataTable(noticeList);
    }

    @ResponseBody
    @GetMapping("/borrow_urge_paging")
    public TableDataInfo borrowUrgePaging(Integer pageNo, Integer pageSize){
        if(ShiroUtils.getSysUser() == null){
            throw new RuntimeException("用户未登录");
        }
        PageHelper.startPage(pageNo, pageSize, "(now()-return_at) desc");
        Borrow borrow = new Borrow();
        borrow.setUserId(ShiroUtils.getUserId());
        borrow.setUrgeReturn(1);
        List<Borrow> borrowList = borrowService.selectBorrowList(borrow);
        return getDataTable(borrowList);
    }


    @ResponseBody
    @GetMapping("/content/{noticeId}")
    public SysNotice content(@PathVariable Long noticeId){
        return sysNoticeService.selectNoticeById(noticeId);
    }
}
