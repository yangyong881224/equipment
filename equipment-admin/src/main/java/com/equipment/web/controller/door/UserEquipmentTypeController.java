package com.equipment.web.controller.door;

import com.equipment.system.domain.EquipmentType;
import com.equipment.system.service.IEquipmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @EquipmentTypeController:
 * @author: Yayo
 * @date: 2021/3/21 16:15
 */
@Controller
@RequestMapping("/user/equipment_type")
public class UserEquipmentTypeController {

    @Autowired
    private IEquipmentTypeService equipmentTypeService;

    @GetMapping("/list")
    @ResponseBody
    public List<EquipmentType> list(){
        return equipmentTypeService.selectEquipmentTypeList(new EquipmentType());
    }
}
