package com.equipment.system.enums;

/**
 * @BorrowFlagEnum:
 * @author: Yayo
 * @date: 2021/3/21 13:42
 */
public enum BorrowFlagEnum {

    WAIT_BORROW_EXAMINE(0, "等待借用审批"),
    BORROWING(1, "正在借用"),
    WAIT_RETURN_EXAMINE(2, "等待归还审批"),
    RETURN_BACK(3, "已归还");

    private Integer code;
    private String desc;

    BorrowFlagEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
