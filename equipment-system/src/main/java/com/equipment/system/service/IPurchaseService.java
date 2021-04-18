package com.equipment.system.service;

import com.equipment.system.domain.Purchase;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @IPuchaseService:
 * @author: Yayo
 * @date: 2021/4/18 13:40
 */
public interface IPurchaseService {

    /**
     * 根据用户id进行查询
     * @param userId
     * @return
     */
    public List<Purchase> findByUserId(Long userId);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public Boolean delete(Long id);

    /**
     * 创建
     * @param purchase
     * @return
     */
    public Boolean create(Purchase purchase);


}
