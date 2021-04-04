package com.equipment.system.service.impl;

import com.equipment.common.utils.DateUtils;
import com.equipment.system.domain.Equipment;
import com.equipment.system.domain.EquipmentType;
import com.equipment.system.domain.Statistics;
import com.equipment.system.mapper.BorrowMapper;
import com.equipment.system.mapper.EquipmentMapper;
import com.equipment.system.mapper.EquipmentTypeMapper;
import com.equipment.system.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @StatisticsServiceImpl:
 * @author: Yayo
 * @date: 2021/4/4 22:20
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Autowired
    private EquipmentTypeMapper equipmentTypeMapper;

    @Autowired
    private BorrowMapper borrowMapper;

    @Override
    public List<Statistics> exportStatistics() {
        List<Equipment> equipmentList = equipmentMapper.selectEquipmentList(new Equipment());
        List<EquipmentType> equipmentTypeList = equipmentTypeMapper.selectEquipmentTypeByIds(equipmentList.stream().map(Equipment::getTypeId).collect(Collectors.toList()));
        List<Statistics> statisticsList = equipmentList.stream().map(equipment -> {
            Statistics statistics = new Statistics();
            statistics.setEquipmentName(equipment.getName());
            statistics.setTotalCount(equipment.getQuantity());
            statistics.setFlag(equipment.getFlag());
            statistics.setBorrowCount(equipment.getBorrowNum());
            EquipmentType type = equipmentTypeList.stream().filter(equipmentType -> equipment.getTypeId().equals(equipmentType.getId())).findFirst().orElse(new EquipmentType());
            statistics.setEquipmentType(type.getTypeName());
//            Long overTimeCount = borrowMapper.selectOverTimeCountByEquipmentId(equipment.getId());
//            Long urgeTimeCount = borrowMapper.selectUrgeCountByEquipmentId(equipment.getId());
            return statistics;
        }).collect(Collectors.toList());
        return statisticsList;
    }
}
