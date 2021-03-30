package com.equipment.system.enums;

/**
 * @BorrowUserStatus:
 * @author: Yayo
 * @date: 2021/3/30 14:45
 */
public enum BorrowUserStatus {
    BORROW_APPLY(0,"正在申请"),
    BORROW_NOW(1,"正在借用"),
    BORROW_OVERDUE(2, "逾期未还"),
    BORROW_EXAMINE(3,"归还审核"),
    BORROW_HISTORY(4,"归还历史"),
    BORROW_REFUSE(5,"被拒申请");

    private int code;
    private String desc;

    BorrowUserStatus(int code , String desc){
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
