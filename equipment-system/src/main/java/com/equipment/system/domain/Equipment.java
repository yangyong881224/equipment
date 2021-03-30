package com.equipment.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.equipment.common.annotation.Excel;
import com.equipment.common.core.domain.BaseEntity;

/**
 * 设备管理对象 equipment
 * 
 * @author equipment
 * @date 2021-01-05
 */
public class Equipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备id */
    private Long id;

    /**设备名称*/
    @Excel(name = "设备名称")
    private String name;
    /** 设备类型id */
    @Excel(name = "设备类型id")
    private Long typeId;
    //设备图片地址
    private String path;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String modelNum;

    /** 设备数量 */
    @Excel(name = "设备数量")
    private Integer quantity;

    /** 设备状态：0正常；1报销 */
    @Excel(name = "设备状态：0正常；1报销")
    private Integer flag;

    @Excel(name = "被借用次数")
    private Long borrowNum;

    /** $column.columnComment */
    @Excel(name = "设备状态：0正常；1报销", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** $column.columnComment */
    @Excel(name = "设备状态：0正常；1报销")
    private String createdBy;
    @Excel(name = "借走数量")
    private Integer borrowQuantity;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getBorrowQuantity() {
        return borrowQuantity;
    }

    public void setBorrowQuantity(Integer borrowQuantity) {
        this.borrowQuantity = borrowQuantity;
    }

    public Long getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(Long borrowNum) {
        this.borrowNum = borrowNum;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", path='" + path + '\'' +
                ", modelNum='" + modelNum + '\'' +
                ", quantity=" + quantity +
                ", flag=" + flag +
                ", borrowNum=" + borrowNum +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", borrowQuantity=" + borrowQuantity +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
