package com.equipment.system.domain;

import com.equipment.common.annotation.Excel;

/**
 * @Statistics:
 * @author: Yayo
 * @date: 2021/4/4 21:43
 */
public class Statistics {

    @Excel(name = "设备名称")
    private String equipmentName;

    @Excel(name = "设备类型")
    private String equipmentType;
    @Excel(name = "设备总数")
    private Integer totalCount;
    @Excel(name = "被借次数")
    private Long borrowCount;
    @Excel(name = "超时次数")
    private Long overTimeCount;
    @Excel(name = "催还次数")
    private Long urgeCount;
    @Excel(name = "是否报销", readConverterExp = "0=正常,1=报销")
    private Integer flag;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Long getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Long borrowCount) {
        this.borrowCount = borrowCount;
    }

    public Long getOverTimeCount() {
        return overTimeCount;
    }

    public void setOverTimeCount(Long overTimeCount) {
        this.overTimeCount = overTimeCount;
    }

    public Long getUrgeCount() {
        return urgeCount;
    }

    public void setUrgeCount(Long urgeCount) {
        this.urgeCount = urgeCount;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
