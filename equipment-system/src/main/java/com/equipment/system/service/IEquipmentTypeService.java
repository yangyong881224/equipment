package com.equipment.system.service;

import java.util.List;
import com.equipment.system.domain.EquipmentType;

/**
 * 类型管理Service接口
 * 
 * @author equipment
 * @date 2021-01-05
 */
public interface IEquipmentTypeService 
{
    /**
     * 查询类型管理
     * 
     * @param id 类型管理ID
     * @return 类型管理
     */
    public EquipmentType selectEquipmentTypeById(Long id);

    /**
     * 查询类型管理列表
     * 
     * @param equipmentType 类型管理
     * @return 类型管理集合
     */
    public List<EquipmentType> selectEquipmentTypeList(EquipmentType equipmentType);

    /**
     * 新增类型管理
     * 
     * @param equipmentType 类型管理
     * @return 结果
     */
    public int insertEquipmentType(EquipmentType equipmentType);

    /**
     * 修改类型管理
     * 
     * @param equipmentType 类型管理
     * @return 结果
     */
    public int updateEquipmentType(EquipmentType equipmentType);

    /**
     * 批量删除类型管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEquipmentTypeByIds(String ids);

    /**
     * 删除类型管理信息
     * 
     * @param id 类型管理ID
     * @return 结果
     */
    public int deleteEquipmentTypeById(Long id);
}
