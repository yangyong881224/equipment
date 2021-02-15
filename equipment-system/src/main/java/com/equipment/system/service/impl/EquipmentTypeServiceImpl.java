package com.equipment.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.equipment.system.mapper.EquipmentTypeMapper;
import com.equipment.system.domain.EquipmentType;
import com.equipment.system.service.IEquipmentTypeService;
import com.equipment.common.core.text.Convert;

/**
 * 类型管理Service业务层处理
 * 
 * @author equipment
 * @date 2021-01-05
 */
@Service
public class EquipmentTypeServiceImpl implements IEquipmentTypeService 
{
    @Autowired
    private EquipmentTypeMapper equipmentTypeMapper;

    /**
     * 查询类型管理
     * 
     * @param id 类型管理ID
     * @return 类型管理
     */
    @Override
    public EquipmentType selectEquipmentTypeById(Long id)
    {
        return equipmentTypeMapper.selectEquipmentTypeById(id);
    }

    /**
     * 查询类型管理列表
     * 
     * @param equipmentType 类型管理
     * @return 类型管理
     */
    @Override
    public List<EquipmentType> selectEquipmentTypeList(EquipmentType equipmentType)
    {
        return equipmentTypeMapper.selectEquipmentTypeList(equipmentType);
    }

    /**
     * 新增类型管理
     * 
     * @param equipmentType 类型管理
     * @return 结果
     */
    @Override
    public int insertEquipmentType(EquipmentType equipmentType)
    {
        return equipmentTypeMapper.insertEquipmentType(equipmentType);
    }

    /**
     * 修改类型管理
     * 
     * @param equipmentType 类型管理
     * @return 结果
     */
    @Override
    public int updateEquipmentType(EquipmentType equipmentType)
    {
        return equipmentTypeMapper.updateEquipmentType(equipmentType);
    }

    /**
     * 删除类型管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentTypeByIds(String ids)
    {
        return equipmentTypeMapper.deleteEquipmentTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除类型管理信息
     * 
     * @param id 类型管理ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentTypeById(Long id)
    {
        return equipmentTypeMapper.deleteEquipmentTypeById(id);
    }
}
