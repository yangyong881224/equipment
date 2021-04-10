package com.equipment.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.equipment.system.mapper.EquipmentMapper;
import com.equipment.system.domain.Equipment;
import com.equipment.system.service.IEquipmentService;
import com.equipment.common.core.text.Convert;

/**
 * 设备管理Service业务层处理
 * 
 * @author equipment
 * @date 2021-01-05
 */
@Service
public class EquipmentServiceImpl implements IEquipmentService 
{
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * 查询设备管理
     * 
     * @param id 设备管理ID
     * @return 设备管理
     */
    @Override
    public Equipment selectEquipmentById(Long id)
    {
        return equipmentMapper.selectEquipmentById(id);
    }

    /**
     * 查询设备管理列表
     * 
     * @param equipment 设备管理
     * @return 设备管理
     */
    @Override
    public List<Equipment> selectEquipmentList(Equipment equipment)
    {
        return equipmentMapper.selectEquipmentList(equipment);
    }

    /**
     * 新增设备管理
     * 
     * @param equipment 设备管理
     * @return 结果
     */
    @Override
    public int insertEquipment(Equipment equipment)
    {
        return equipmentMapper.insertEquipment(equipment);
    }

    /**
     * 修改设备管理
     * 
     * @param equipment 设备管理
     * @return 结果
     */
    @Override
    public int updateEquipment(Equipment equipment)
    {
        return equipmentMapper.updateEquipment(equipment);
    }

    /**
     * 删除设备管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentByIds(String ids)
    {
        return equipmentMapper.deleteEquipmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentById(Long id)
    {
        return equipmentMapper.deleteEquipmentById(id);
    }

    @Override
    public int agreeBorrow(Long equipmentId) {
        return equipmentMapper.agreeBorrow(equipmentId);
    }

    @Override
    public int agreeReturn(Long equipmentId) {
        return equipmentMapper.agreeReturn(equipmentId);
    }
}
