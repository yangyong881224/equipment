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

    @GetMapping
    public String notice(ModelMap mmap){
        if(ShiroUtils.getSysUser()!=null){
            mmap.put("user",ShiroUtils.getSysUser());
        }
        return "/door/news";
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
        PageHelper.startPage(pageNo, pageSize, "(now()-return_at) desc");
        Borrow borrow = new Borrow();
        borrow.setUrgeFlag(1);
        borrow.setFlag(BorrowFlagEnum.RETURN_BACK.getCode());
        List<Borrow> borrowList = borrowService.selectBorrowList(borrow);
        return getDataTable(borrowList);
    }


    @ResponseBody
    @GetMapping("/{noticeId}")
    public SysNotice content(@PathVariable Long noticeId){
        return sysNoticeService.selectNoticeById(noticeId);
    }
}
