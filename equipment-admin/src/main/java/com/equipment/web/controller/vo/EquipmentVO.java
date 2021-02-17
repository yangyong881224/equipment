package com.equipment.web.controller.vo;

import com.equipment.common.annotation.Excel;
import com.equipment.system.domain.Equipment;

import java.util.Date;

/**
 * @EquipmentListVO:
 * @author: Yayo
 * @date: 2021/2/15 23:07
 */
public class EquipmentVO {

    private String typeName;

    @Excel(name = "设备名称")
    private String name;

    /** 设备id */
    private Long id;

    /** 设备类型id */
    @Excel(name = "设备类型id")
    private Long typeId;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String modelNum;

    /** 设备数量 */
    @Excel(name = "设备数量")
    private Integer quantity;

    /** 设备状态：0正常；1报销 */
    @Excel(name = "设备状态：0正常；1报销")
    private Integer flag;

    /** $column.columnComment */
    @Excel(name = "设备状态：0正常；1报销", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** $column.columnComment */
    @Excel(name = "设备状态：0正常；1报销")
    private String createdBy;

    /** $column.columnComment */
    @Excel(name = "设备状态：0正常；1报销", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    /** $column.columnComment */
    @Excel(name = "设备状态：0正常；1报销")
    private String updatedBy;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId()
    {
        return typeId;
    }
    public void setModelNum(String modelNum)
    {
        this.modelNum = modelNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelNum()
    {
        return modelNum;
    }
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getQuantity()
    {
        return quantity;
    }
    public void setFlag(Integer flag)
    {
        this.flag = flag;
    }

    public Integer getFlag()
    {
        return flag;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
