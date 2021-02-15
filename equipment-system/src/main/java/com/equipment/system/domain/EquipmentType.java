package com.equipment.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.equipment.common.annotation.Excel;
import com.equipment.common.core.domain.BaseEntity;

/**
 * 类型管理对象 equipment_type
 * 
 * @author equipment
 * @date 2021-01-05
 */
public class EquipmentType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备类型id */
    private Long id;

    /** 设备类型名称 */
    @Excel(name = "设备类型名称")
    private String typeName;

    /** 0正常；1删除 */
    @Excel(name = "0正常；1删除")
    private Integer deleted;

    /** $column.columnComment */
    @Excel(name = "0正常；1删除", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** $column.columnComment */
    @Excel(name = "0正常；1删除")
    private String createdBy;

    /** $column.columnComment */
    @Excel(name = "0正常；1删除", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    /** $column.columnComment */
    @Excel(name = "0正常；1删除")
    private String updatedBy;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("typeName", getTypeName())
            .append("deleted", getDeleted())
            .append("createdAt", getCreatedAt())
            .append("createdBy", getCreatedBy())
            .append("updatedAt", getUpdatedAt())
            .append("updatedBy", getUpdatedBy())
            .toString();
    }
}
