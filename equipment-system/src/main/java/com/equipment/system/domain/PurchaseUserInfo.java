package com.equipment.system.domain;

/**
 * @PurchaseUserInfo:
 * @author: Yayo
 * @date: 2021/4/18 21:57
 */
public class PurchaseUserInfo {

    private Long id;
    private Long userId;
    private Long equipmentId;
    private String equipmentPath;
    private String equipmentName;
    private String flag;

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

    public String getEquipmentPath() {
        return equipmentPath;
    }

    public void setEquipmentPath(String equipmentPath) {
        this.equipmentPath = equipmentPath;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
