package com.equipment.system.mapper;

import java.util.List;
import com.equipment.system.domain.Equipment;

/**
 * 设备管理Mapper接口
 * 
 * @author equipment
 * @date 2021-01-05
 */
public interface EquipmentMapper 
{
    /**
     * 查询设备管理
     * 
     * @param id 设备管理ID
     * @return 设备管理
     */
    public Equipment selectEquipmentById(Long id);

    /**
     * 查询设备管理列表
     * 
     * @param equipment 设备管理
     * @return 设备管理集合
     */
    public List<Equipment> selectEquipmentList(Equipment equipment);

    /**
     * 新增设备管理
     * 
     * @param equipment 设备管理
     * @return 结果
     */
    public int insertEquipment(Equipment equipment);

    /**
     * 修改设备管理
     * 
     * @param equipment 设备管理
     * @return 结果
     */
    public int updateEquipment(Equipment equipment);

    /**
     * 删除设备管理
     * 
     * @param id 设备管理ID
     * @return 结果
     */
    public int deleteEquipmentById(Long id);

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEquipmentByIds(String[] ids);
}
