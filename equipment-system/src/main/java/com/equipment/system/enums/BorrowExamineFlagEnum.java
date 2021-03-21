package com.equipment.system.enums;

/**
 * @BorrowExamineFlagEnum:
 * @author: Yayo
 * @date: 2021/3/21 13:47
 */
public enum BorrowExamineFlagEnum {

    NOT_EXAMINE(0, "未审批"),
    AGREE_BORROW(1, "同意借用"),
    REFUSE_BORROW(2, "拒绝借用"),
    AGREE_RETURN_BACK(3, "同意归还"),
    REFUSE_RETURN_BACK(4, "拒绝归还");

    private int code;
    private String desc;

    BorrowExamineFlagEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
