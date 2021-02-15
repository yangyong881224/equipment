package com.equipment.web.controller.system;

import java.util.Date;
import java.util.List;

import com.equipment.common.utils.ShiroUtils;
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
import com.equipment.system.domain.EquipmentType;
import com.equipment.system.service.IEquipmentTypeService;
import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.domain.AjaxResult;
import com.equipment.common.utils.poi.ExcelUtil;
import com.equipment.common.core.page.TableDataInfo;

/**
 * 类型管理Controller
 * 
 * @author equipment
 * @date 2021-01-05
 */
@Controller
@RequestMapping("/system/type")
public class EquipmentTypeController extends BaseController
{
    private String prefix = "system/type";

    @Autowired
    private IEquipmentTypeService equipmentTypeService;

    @RequiresPermissions("system:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    /**
     * 查询类型管理列表
     */
    @RequiresPermissions("system:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EquipmentType equipmentType)
    {
        startPage();
        List<EquipmentType> list = equipmentTypeService.selectEquipmentTypeList(equipmentType);
        return getDataTable(list);
    }

    /**
     * 导出类型管理列表
     */
    @RequiresPermissions("system:type:export")
    @Log(title = "类型管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EquipmentType equipmentType)
    {
        List<EquipmentType> list = equipmentTypeService.selectEquipmentTypeList(equipmentType);
        ExcelUtil<EquipmentType> util = new ExcelUtil<EquipmentType>(EquipmentType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 新增类型管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存类型管理
     */
    @RequiresPermissions("system:type:add")
    @Log(title = "类型管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EquipmentType equipmentType)
    {
        equipmentType.setCreatedAt(new Date());
        equipmentType.setUpdatedAt(new Date());
        equipmentType.setCreatedBy(ShiroUtils.getSysUser().getUserName());
        equipmentType.setUpdatedBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(equipmentTypeService.insertEquipmentType(equipmentType));
    }

    /**
     * 修改类型管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EquipmentType equipmentType = equipmentTypeService.selectEquipmentTypeById(id);
        mmap.put("equipmentType", equipmentType);
        return prefix + "/edit";
    }

    /**
     * 修改保存类型管理
     */
    @RequiresPermissions("system:type:edit")
    @Log(title = "类型管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EquipmentType equipmentType)
    {
        equipmentType.setUpdatedAt(new Date());
        equipmentType.setUpdatedBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(equipmentTypeService.updateEquipmentType(equipmentType));
    }

    /**
     * 删除类型管理
     */
    @RequiresPermissions("system:type:remove")
    @Log(title = "类型管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(equipmentTypeService.deleteEquipmentTypeByIds(ids));
    }
}
