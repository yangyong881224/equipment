package com.equipment.system.vo;

import java.util.Date;
import java.util.List;

/**
 * @BorrowAndExamineVO:
 * @author: Yayo
 * @date: 2021/4/4 12:53
 */
public class BorrowAndExamineVO {

    private String week;

    private Long borrowCount;

    private Long agreeCount;

    private Long refuseCount;

    private Date groupDate;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Long getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Long borrowCount) {
        this.borrowCount = borrowCount;
    }

    public Long getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(Long agreeCount) {
        this.agreeCount = agreeCount;
    }

    public Long getRefuseCount() {
        return refuseCount;
    }

    public void setRefuseCount(Long refuseCount) {
        this.refuseCount = refuseCount;
    }

    public Date getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(Date groupDate) {
        this.groupDate = groupDate;
    }
}
