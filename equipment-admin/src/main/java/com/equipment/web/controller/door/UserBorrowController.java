package com.equipment.web.controller.door;

import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.page.TableDataInfo;
import com.equipment.common.utils.ShiroUtils;
import com.equipment.system.domain.Borrow;
import com.equipment.system.enums.BorrowExamineFlagEnum;
import com.equipment.system.enums.BorrowFlagEnum;
import com.equipment.system.enums.BorrowUserStatus;
import com.equipment.system.service.IBorrowService;
import com.equipment.web.controller.vo.BorrowVO;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @UserBorrowController:
 * @author: Yayo
 * @date: 2021/3/29 3:22
 */
@Controller
@RequestMapping("/user/borrow")
public class UserBorrowController extends BaseController {

    @Autowired
    private IBorrowService borrowService;

    @GetMapping
    public String borrow(ModelMap mmap){
        if(ShiroUtils.getSysUser() == null){
            return "redirect:/user/index";
        }else{
            mmap.put("user",ShiroUtils.getSysUser());
            return "/door/borrow";
        }
    }

    @PostMapping
    @ResponseBody
    public boolean borrow(Borrow borrow){
        if(ShiroUtils.getSysUser() == null){
            return false;
        }

        borrow.setFlag(0);
        borrow.setUserId(ShiroUtils.getUserId());
        borrow.setUserName(ShiroUtils.getSysUser().getUserName());
        borrow.setExamineFlag(0);
        borrow.setUrgeReturn(0);
        return borrowService.insertBorrow(borrow)==1;
    }

    @GetMapping("/paging")
    @ResponseBody
    public TableDataInfo paging(BorrowVO borrowVO) throws Exception {
        if(ShiroUtils.getSysUser()==null){
            throw new RuntimeException("用户未登录");
        }
        startPage();
        Borrow borrow = new Borrow();
        borrow.setUserId(ShiroUtils.getUserId());
        if(BorrowUserStatus.BORROW_APPLY.name().equals(borrowVO.getUserStatus())){
            borrow.setFlag(BorrowFlagEnum.WAIT_BORROW_EXAMINE.getCode());
            borrow.setExamineFlag(BorrowFlagEnum.WAIT_BORROW_EXAMINE.getCode());
        }else if(BorrowUserStatus.BORROW_NOW.name().equals(borrowVO.getUserStatus())){
            borrow.setFlag(BorrowFlagEnum.BORROWING.getCode());
        }else if(BorrowUserStatus.BORROW_OVERDUE.name().equals(borrowVO.getUserStatus())){// 过期未还查询
            List<Borrow> borrowList = borrowService.selectOverdueList(borrow);
            return getDataTable(borrowList.stream().map(b ->{
                BorrowVO vo = new BorrowVO();
                BeanUtils.copyProperties(b,borrowVO);
                return vo;
            }).collect(Collectors.toList()));
        }else if(BorrowUserStatus.BORROW_EXAMINE.name().equals(borrowVO.getUserStatus())){
            borrow.setFlag(BorrowFlagEnum.WAIT_RETURN_EXAMINE.getCode());
        }else if(BorrowUserStatus.BORROW_HISTORY.name().equals(borrowVO.getUserStatus())){
            borrow.setFlag(BorrowFlagEnum.RETURN_BACK.getCode());
            borrow.setExamineFlag(BorrowExamineFlagEnum.AGREE_RETURN_BACK.getCode());
        }else if(BorrowUserStatus.BORROW_REFUSE.name().equals(borrowVO.getUserStatus())){//全部拒绝查询
            List<Borrow> borrowList = borrowService.selectRefuseList(borrow);
            return getDataTable(borrowList.stream().map(b ->{
                BorrowVO vo = new BorrowVO();
                BeanUtils.copyProperties(b,borrowVO);
                return vo;
            }).collect(Collectors.toList()));
        }else{
            return getDataTable(Lists.newArrayList());
        }
        List<Borrow> borrowList = borrowService.selectBorrowList(borrow);

        return getDataTable(borrowList.stream().map(b ->{
            BorrowVO vo = new BorrowVO();
            BeanUtils.copyProperties(b,borrowVO);
            return vo;
        }).collect(Collectors.toList()));
    }


}
