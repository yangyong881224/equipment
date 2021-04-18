package com.equipment.system.mapper;

import com.equipment.system.domain.Purchase;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @PurchaseMapper:
 * @author: Yayo
 * @date: 2021/4/18 13:36
 */
public interface PurchaseMapper {

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
    public Integer delete(Long id);

    /**
     * 创建
     * @param purchase
     * @return
     */
    public Integer create(Purchase purchase);


}
