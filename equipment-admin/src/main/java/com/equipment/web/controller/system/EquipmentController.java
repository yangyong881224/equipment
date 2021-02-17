package com.equipment.web.controller.system;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.equipment.common.utils.ShiroUtils;
import com.equipment.system.domain.EquipmentType;
import com.equipment.system.service.IEquipmentTypeService;
import com.equipment.web.controller.converter.EquipmentVOConverter;
import com.equipment.web.controller.vo.EquipmentVO;
import com.google.common.collect.Lists;
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
import com.equipment.system.domain.Equipment;
import com.equipment.system.service.IEquipmentService;
import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.domain.AjaxResult;
import com.equipment.common.utils.poi.ExcelUtil;
import com.equipment.common.core.page.TableDataInfo;

/**
 * 设备管理Controller
 * 
 * @author equipment
 * @date 2021-01-05
 */
@Controller
@RequestMapping("/system/equipment")
public class EquipmentController extends BaseController
{

    private String prefix = "system/equipment";

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IEquipmentTypeService equipmentTypeService;

    @Autowired
    private EquipmentVOConverter equipmentVOConverter;

    @RequiresPermissions("system:equipment:view")
    @GetMapping()
    public String equipment()
    {
        return prefix + "/equipment";
    }

    /**
     * 查询设备管理列表
     */
    @RequiresPermissions("system:equipment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Equipment equipment)
    {
        startPage();
        List<Equipment> list = equipmentService.selectEquipmentList(equipment);
        List<EquipmentVO> equipmentListVOList = insertTypeName(list);
        return getDataTable(equipmentListVOList);
    }

    /**
     * 导出设备管理列表
     */
    @RequiresPermissions("system:equipment:export")
    @Log(title = "设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Equipment equipment)
    {
        List<Equipment> list = equipmentService.selectEquipmentList(equipment);
        ExcelUtil<EquipmentVO> util = new ExcelUtil<EquipmentVO>(EquipmentVO.class);
        List<EquipmentVO> equipmentListVOList = insertTypeName(list);
        return util.exportExcel(equipmentListVOList, "equipment");
    }

    /**
     * 数据拼装
     * @param equipmentList
     * @return
     */
    public List<EquipmentVO> insertTypeName(List<Equipment> equipmentList){
        List<EquipmentVO> equipmentVOList = Lists.newArrayList();
        if(equipmentList == null || equipmentList.size() == 0)
            return equipmentVOList;
        List<Long> typeIdList = equipmentList.stream().map(Equipment::getTypeId).collect(Collectors.toList());
        //查询typeName
        List<EquipmentType> equipmentTypeList = equipmentTypeService.selectEquipmentTypeByIds(typeIdList);
        //对比拼接
        equipmentList.stream().forEach(equipment -> {
            EquipmentType equipmentType = equipmentTypeList.stream().filter(type -> type.getId().equals(equipment.getTypeId())).findFirst().orElse(null);
            EquipmentVO equipmentVO = equipmentVOConverter.equipment2VO(equipment);
            if(equipmentType != null){
                equipmentVO.setTypeName(equipmentType.getTypeName());
            }
            //数据并集
            equipmentVOList.add(equipmentVO);

        });
        return equipmentVOList;
    }

    /**
     * 新增设备管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存设备管理
     */
    @RequiresPermissions("system:equipment:add")
    @Log(title = "设备管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Equipment equipment)
    {
        equipment.setCreatedAt(new Date());
        equipment.setCreatedBy(ShiroUtils.getSysUser().getUserName());
        equipment.setUpdatedAt(new Date());
        equipment.setUpdatedBy(ShiroUtils.getSysUser().getUserName());
        equipment.setFlag(0);
        return toAjax(equipmentService.insertEquipment(equipment));
    }

    /**
     * 修改设备管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Equipment equipment = equipmentService.selectEquipmentById(id);
        mmap.put("equipment", equipment);
        return prefix + "/edit";
    }

    /**
     * 修改保存设备管理
     */
    @RequiresPermissions("system:equipment:edit")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Equipment equipment)
    {
        equipment.setUpdatedAt(new Date());
        equipment.setUpdatedBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(equipmentService.updateEquipment(equipment));
    }

    /**
     * 删除设备管理
     */
    @RequiresPermissions("system:equipment:remove")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(equipmentService.deleteEquipmentByIds(ids));
    }


}
