package com.equipment.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.equipment.common.annotation.Excel;
import com.equipment.common.core.domain.BaseEntity;

/**
 * banner图管理对象 equipment_banner
 * 
 * @author equipment
 * @date 2021-03-28
 */
public class EquipmentBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** banner主键ID */
    private Long id;

    /** 设备表主键ID */
    @Excel(name = "设备表主键ID")
    private Long equipmentId;

    /** banner名称 */
    @Excel(name = "banner名称")
    private String bannerName;

    /** banner地址 */
    @Excel(name = "banner地址")
    private String bannerPath;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 状态(0启用 1关闭) */
    @Excel(name = "状态(0启用 1关闭)")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEquipmentId(Long equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() 
    {
        return equipmentId;
    }
    public void setBannerName(String bannerName) 
    {
        this.bannerName = bannerName;
    }

    public String getBannerName() 
    {
        return bannerName;
    }
    public void setBannerPath(String bannerPath) 
    {
        this.bannerPath = bannerPath;
    }

    public String getBannerPath() 
    {
        return bannerPath;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipmentId", getEquipmentId())
            .append("bannerName", getBannerName())
            .append("bannerPath", getBannerPath())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
