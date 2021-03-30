package com.equipment.web.controller.system;

import com.equipment.common.annotation.Log;
import com.equipment.common.config.ServerConfig;
import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.domain.AjaxResult;
import com.equipment.common.core.page.TableDataInfo;
import com.equipment.common.enums.BusinessType;
import com.equipment.common.utils.poi.ExcelUtil;
import com.equipment.system.domain.EquipmentBanner;
import com.equipment.system.service.IEquipmentBannerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * banner图管理Controller
 *
 * @author equipment
 * @date 2021-03-28
 */
@Controller
@RequestMapping("/system/banner")
public class EquipmentBannerController extends BaseController
{
    private String prefix = "system/banner";

    @Autowired
    private IEquipmentBannerService equipmentBannerService;
    @Autowired
    private ServerConfig serverConfig;

    @RequiresPermissions("system:banner:view")
    @GetMapping()
    public String banner()
    {
        return prefix + "/banner";
    }

    /**
     * 查询banner图管理列表
     */
    @RequiresPermissions("system:banner:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EquipmentBanner equipmentBanner)
    {
        startPage();
        List<EquipmentBanner> list = equipmentBannerService.selectEquipmentBannerList(equipmentBanner);
        return getDataTable(list);
    }

    /**
     * 导出banner图管理列表
     */
    @RequiresPermissions("system:banner:export")
    @Log(title = "banner图管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EquipmentBanner equipmentBanner)
    {
        List<EquipmentBanner> list = equipmentBannerService.selectEquipmentBannerList(equipmentBanner);
        ExcelUtil<EquipmentBanner> util = new ExcelUtil<EquipmentBanner>(EquipmentBanner.class);
        return util.exportExcel(list, "banner");
    }

    /**
     * 新增banner图管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存banner图管理
     */
    @RequiresPermissions("system:banner:add")
    @Log(title = "banner图管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EquipmentBanner equipmentBanner)
    {
        return toAjax(equipmentBannerService.insertEquipmentBanner(equipmentBanner));
    }

    /**
     * 修改banner图管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EquipmentBanner equipmentBanner = equipmentBannerService.selectEquipmentBannerById(id);
        mmap.put("equipmentBanner", equipmentBanner);
        return prefix + "/edit";
    }

    /**
     * 修改保存banner图管理
     */
    @RequiresPermissions("system:banner:edit")
    @Log(title = "banner图管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EquipmentBanner equipmentBanner)
    {
        return toAjax(equipmentBannerService.updateEquipmentBanner(equipmentBanner));
    }

    /**
     * 删除banner图管理
     */
    @RequiresPermissions("system:banner:remove")
    @Log(title = "banner图管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(equipmentBannerService.deleteEquipmentBannerByIds(ids));
    }

    /**
     * banner状态修改
     */
    @RequiresPermissions("system:banner:edit")
    @Log(title = "banner状态修改", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(EquipmentBanner equipmentBanner)
    {
        return toAjax(equipmentBannerService.changeStatus(equipmentBanner));
    }
}
