package com.equipment.web.controller.converter;

import com.equipment.system.domain.Equipment;
import com.equipment.web.controller.vo.EquipmentVO;
import org.mapstruct.Mapper;

/**
 * @EquipmentVOConverter:
 * @author: Yayo
 * @date: 2021/2/16 21:54
 */
@Mapper(componentModel = "spring")
public interface EquipmentVOConverter {

    public EquipmentVO equipment2VO(Equipment equipment);

}
