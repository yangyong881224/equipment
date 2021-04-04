package com.equipment.web.controller.system;

import com.equipment.common.annotation.Log;
import com.equipment.common.core.controller.BaseController;
import com.equipment.common.core.domain.AjaxResult;
import com.equipment.common.core.page.TableDataInfo;
import com.equipment.common.enums.BusinessType;
import com.equipment.common.utils.ShiroUtils;
import com.equipment.common.utils.poi.ExcelUtil;
import com.equipment.system.domain.Borrow;
import com.equipment.system.domain.Statistics;
import com.equipment.system.enums.BorrowExamineFlagEnum;
import com.equipment.system.enums.BorrowFlagEnum;
import com.equipment.system.service.IBorrowService;
import com.equipment.system.service.IStatisticsService;
import com.equipment.system.vo.BorrowAndExamineVO;
import com.equipment.system.vo.FavoriteEquipmentVO;
import com.google.common.collect.Lists;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计管理Controller
 *
 * @author equipment
 * @date 2021-02-28
 */
@Controller
@RequestMapping("/system/statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private IBorrowService borrowService;

    @Autowired
    private IStatisticsService statisticsService;

    private String prefix = "system/statistics";

    @RequiresPermissions("system:statistics:view")
    @GetMapping()
    public String statistics()
    {
        return prefix + "/statistics";
    }

    /**
     * 统计本周借用和审核情况
     */
    @GetMapping("/borrow_and_examine")
    @ResponseBody
    public Map<String, String[]> borrowAndExamine()
    {
        Map<String, String[]> result = borrowService.borrowAndExamineStatistics();
        return result;
    }


    @GetMapping("/favorite_equipment")
    @ResponseBody
    public Map<String, Object> favoriteEquipment(){
        return borrowService.favoriteEquipment();
    }

    @GetMapping("/return_statistics")
    @ResponseBody
    public Map<String,Object> returnStatistics(){
        return borrowService.returnStatistics();
    }

    /**
     * 导出借用管理列表
     */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Borrow borrow){
        List<Statistics> list = statisticsService.exportStatistics();
        ExcelUtil<Statistics> util = new ExcelUtil<Statistics>(Statistics.class);
        return util.exportExcel(list, "设备借用统计");
    }

}