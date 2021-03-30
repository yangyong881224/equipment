package com.equipment.web.controller.door;

import com.equipment.common.utils.ShiroUtils;
import com.equipment.system.domain.Equipment;
import com.equipment.system.service.IEquipmentService;
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
 * @UserEquipmentController:
 * @author: Yayo
 * @date: 2021/3/21 16:25
 */
@RequestMapping("/user/equipment")
@Controller
public class UserEquipmentController {

    @Autowired
    private IEquipmentService equipmentService;

    @GetMapping
    public String equipment(ModelMap mmap){
        if(ShiroUtils.getSysUser()!=null){
            mmap.put("user",ShiroUtils.getSysUser());
        }
        return "/door/equipment";
    }

    @GetMapping("/detail/{id}")
    public String equipmentDtail(ModelMap mmap, @PathVariable Long id){
        if(ShiroUtils.getSysUser()!=null){
            mmap.put("user",ShiroUtils.getSysUser());
        }
        Equipment equipment = equipmentService.selectEquipmentById(id);
        mmap.put("equipment",equipment);
        return "/door/equipment_detail";
    }

    @GetMapping("/paging")
    @ResponseBody
    public List<Equipment> paging(Long typeId, Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize, "created_at desc");
        Equipment equipment = new Equipment();
        equipment.setTypeId(typeId);
        equipment.setFlag(0);
        return equipmentService.selectEquipmentList(equipment);
    }

    @GetMapping("/pagingFavorite")
    @ResponseBody
    public List<Equipment> pagingFavorite(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize, "borrow_num desc,quantity desc");
        Equipment equipment = new Equipment();
        equipment.setFlag(0);
        return equipmentService.selectEquipmentList(equipment);
    }
}
