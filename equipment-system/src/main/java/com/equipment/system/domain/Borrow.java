package com.equipment.system.domain;

import com.equipment.common.annotation.Excel;
import com.equipment.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    private Integer borrowNum;

    /** 归还日期 */
    @Excel(name = "归还日期", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
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
    private Integer urgeReturn;

    @Excel(name = "实际归还日期")
    private Date realReturnAt;
    //是否超时
    private boolean overtime = false;

    public Integer getBorrowNum() {
        return borrowNum;
    }

    public void setBorrowNum(Integer borrowNum) {
        this.borrowNum = borrowNum;
    }



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
        if(this.returnAt.compareTo(new Date()) <= 0){
            this.overtime = true;
        }
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

    public Date getRealReturnAt() {
        return realReturnAt;
    }

    public void setRealReturnAt(Date realReturnAt) {
        this.realReturnAt = realReturnAt;
    }

    public Integer getUrgeReturn() {
        return urgeReturn;
    }

    public void setUrgeReturn(Integer urgeReturn) {
        this.urgeReturn = urgeReturn;
    }

    public boolean isOvertime() {
        return overtime;
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
                ", urgeReturn=" + urgeReturn +
                ", realReturnAt=" + realReturnAt +
                '}';
    }
}
