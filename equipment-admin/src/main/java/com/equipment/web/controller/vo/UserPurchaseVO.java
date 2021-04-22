package com.equipment.web.controller.vo;

import java.io.Serializable;

/**
 * @UserPurchaseVO:
 * @author: Yayo
 * @date: 2021/4/22 20:33
 */
public class UserPurchaseVO implements Serializable {
    private static final long serialVersionUID = -5185819088040662978L;
    private Long id;
    private Long userId;
    private Long equipmentId;
    private String equipmentName;
    private String path;
    private Integer flag;
    private Integer quantity;

    private Integer borrowQuantity;

    public Integer getBorrowQuantity() {
        return borrowQuantity;
    }

    public void setBorrowQuantity(Integer borrowQuantity) {
        this.borrowQuantity = borrowQuantity;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
