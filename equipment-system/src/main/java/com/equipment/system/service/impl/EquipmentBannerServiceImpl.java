package com.equipment.system.service.impl;

import java.util.List;
import com.equipment.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.equipment.system.mapper.EquipmentBannerMapper;
import com.equipment.system.domain.EquipmentBanner;
import com.equipment.system.service.IEquipmentBannerService;
import com.equipment.common.core.text.Convert;

/**
 * banner图管理Service业务层处理
 *
 * @author equipment
 * @date 2021-03-28
 */
@Service
public class EquipmentBannerServiceImpl implements IEquipmentBannerService
{
    @Autowired
    private EquipmentBannerMapper equipmentBannerMapper;

    /**
     * 查询banner图管理
     *
     * @param id banner图管理ID
     * @return banner图管理
     */
    @Override
    public EquipmentBanner selectEquipmentBannerById(Long id)
    {
        return equipmentBannerMapper.selectEquipmentBannerById(id);
    }

    /**
     * 查询banner图管理列表
     *
     * @param equipmentBanner banner图管理
     * @return banner图管理
     */
    @Override
    public List<EquipmentBanner> selectEquipmentBannerList(EquipmentBanner equipmentBanner)
    {
        return equipmentBannerMapper.selectEquipmentBannerList(equipmentBanner);
    }

    /**
     * 新增banner图管理
     *
     * @param equipmentBanner banner图管理
     * @return 结果
     */
    @Override
    public int insertEquipmentBanner(EquipmentBanner equipmentBanner)
    {
        equipmentBanner.setCreateTime(DateUtils.getNowDate());
        return equipmentBannerMapper.insertEquipmentBanner(equipmentBanner);
    }

    /**
     * 修改banner图管理
     *
     * @param equipmentBanner banner图管理
     * @return 结果
     */
    @Override
    public int updateEquipmentBanner(EquipmentBanner equipmentBanner)
    {
        equipmentBanner.setUpdateTime(DateUtils.getNowDate());
        return equipmentBannerMapper.updateEquipmentBanner(equipmentBanner);
    }

    /**
     * 删除banner图管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentBannerByIds(String ids)
    {
        return equipmentBannerMapper.deleteEquipmentBannerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除banner图管理信息
     *
     * @param id banner图管理ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentBannerById(Long id)
    {
        return equipmentBannerMapper.deleteEquipmentBannerById(id);
    }

    /**
     * banner状态修改
     * @param equipmentBanner
     * @return
     */
    @Override
    public int changeStatus(EquipmentBanner equipmentBanner) {
        return equipmentBannerMapper.updateEquipmentBanner(equipmentBanner);
    }
}
