package com.equipment.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.equipment.common.annotation.Log;
import com.equipment.common.enums.BusinessType;
import com.equipment.system.domain.Borrow;
import com.equipment.system.service.IBorrowService;
import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.domain.AjaxResult;
import com.equipment.common.utils.poi.ExcelUtil;
import com.equipment.common.core.page.TableDataInfo;

/**
 * 借用管理Controller
 *
 * @author equipment
 * @date 2021-02-28
 */
@Controller
@RequestMapping("/system/borrow")
public class BorrowController extends BaseController
{
    private String prefix = "system/borrow";

    @Autowired
    private IBorrowService borrowService;

    @RequiresPermissions("system:borrow:view")
    @GetMapping()
    public String borrow()
    {
        return prefix + "/borrow";
    }

    /**
     * 查询借用管理列表
     */
    @RequiresPermissions("system:borrow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Borrow borrow)
    {
        startPage();
        List<Borrow> list = borrowService.selectBorrowList(borrow);
        return getDataTable(list);
    }

    /**
     * 导出借用管理列表
     */
    @RequiresPermissions("system:borrow:export")
    @Log(title = "借用管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Borrow borrow)
    {
        List<Borrow> list = borrowService.selectBorrowList(borrow);
        ExcelUtil<Borrow> util = new ExcelUtil<Borrow>(Borrow.class);
        return util.exportExcel(list, "borrow");
    }

    /**
     * 新增借用管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存借用管理
     */
    @RequiresPermissions("system:borrow:add")
    @Log(title = "借用管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Borrow borrow)
    {
        return toAjax(borrowService.insertBorrow(borrow));
    }

    /**
     * 修改借用管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Borrow borrow = borrowService.selectBorrowById(id);
        mmap.put("borrow", borrow);
        return prefix + "/edit";
    }

    /**
     * 修改保存借用管理
     */
    @RequiresPermissions("system:borrow:edit")
    @Log(title = "借用管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Borrow borrow)
    {
        return toAjax(borrowService.updateBorrow(borrow));
    }

    /**
     * 删除借用管理
     */
    @RequiresPermissions("system:borrow:remove")
    @Log(title = "借用管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(borrowService.deleteBorrowByIds(ids));
    }
}