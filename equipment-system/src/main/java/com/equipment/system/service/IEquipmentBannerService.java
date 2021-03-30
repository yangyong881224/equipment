package com.equipment.system.service;

import java.util.List;
import com.equipment.system.domain.EquipmentBanner;

/**
 * banner图管理Service接口
 *
 * @author equipment
 * @date 2021-03-28
 */
public interface IEquipmentBannerService
{
    /**
     * 查询banner图管理
     *
     * @param id banner图管理ID
     * @return banner图管理
     */
    public EquipmentBanner selectEquipmentBannerById(Long id);

    /**
     * 查询banner图管理列表
     *
     * @param equipmentBanner banner图管理
     * @return banner图管理集合
     */
    public List<EquipmentBanner> selectEquipmentBannerList(EquipmentBanner equipmentBanner);

    /**
     * 新增banner图管理
     *
     * @param equipmentBanner banner图管理
     * @return 结果
     */
    public int insertEquipmentBanner(EquipmentBanner equipmentBanner);

    /**
     * 修改banner图管理
     *
     * @param equipmentBanner banner图管理
     * @return 结果
     */
    public int updateEquipmentBanner(EquipmentBanner equipmentBanner);

    /**
     * 批量删除banner图管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEquipmentBannerByIds(String ids);

    /**
     * 删除banner图管理信息
     *
     * @param id banner图管理ID
     * @return 结果
     */
    public int deleteEquipmentBannerById(Long id);

    /**
     * banner状态修改
     * @param equipmentBanner
     * @return
     */
    public int changeStatus(EquipmentBanner equipmentBanner);
}
