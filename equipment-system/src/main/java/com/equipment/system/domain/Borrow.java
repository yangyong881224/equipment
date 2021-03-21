package com.equipment.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.equipment.common.annotation.Excel;
import com.equipment.common.core.domain.BaseEntity;

/**
 * 借用管理对象 borrow
 * 
 * @author equipment
 * @date 2021-02-28
 */
public class Borrow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 借用id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 设备id */
    @Excel(name = "设备id")
    private Long equipmentId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String equipmentName;

    /** 借用日期 */
    @Excel(name = "借用日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 归还日期 */
    @Excel(name = "归还日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnAt;

    /** 审批时间 */
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date examineAt;

    /** 审批人id */
    @Excel(name = "审批人id")
    private Long sysUserId;

    /** 审批人 */
    @Excel(name = "审批人")
    private String sysUserName;

    /** 借用状态 */
    @Excel(name = "借用状态")
    private Integer flag;

    @Excel(name = "审批状态")
    private Integer examineFlag;

    @Excel(name = "是否催还")
    private Integer urgeFlag;

    @Excel(name = "实际归还日期")
    private Date realReturnAt;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getReturnAt() {
        return returnAt;
    }

    public void setReturnAt(Date returnAt) {
        this.returnAt = returnAt;
    }

    public Date getExamineAt() {
        return examineAt;
    }

    public void setExamineAt(Date examineAt) {
        this.examineAt = examineAt;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getExamineFlag() {
        return examineFlag;
    }

    public void setExamineFlag(Integer examineFlag) {
        this.examineFlag = examineFlag;
    }

    public Integer getUrgeFlag() {
        return urgeFlag;
    }

    public void setUrgeFlag(Integer urgeFlag) {
        this.urgeFlag = urgeFlag;
    }

    public Date getRealReturnAt() {
        return realReturnAt;
    }

    public void setRealReturnAt(Date realReturnAt) {
        this.realReturnAt = realReturnAt;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", equipmentId=" + equipmentId +
                ", equipmentName='" + equipmentName + '\'' +
                ", createdAt=" + createdAt +
                ", returnAt=" + returnAt +
                ", examineAt=" + examineAt +
                ", sysUserId=" + sysUserId +
                ", sysUserName='" + sysUserName + '\'' +
                ", flag=" + flag +
                ", examineFlag=" + examineFlag +
                ", urgeFlag=" + urgeFlag +
                ", realReturnAt=" + realReturnAt +
                '}';
    }
}
