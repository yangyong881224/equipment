package com.equipment.system.service.impl;

import com.equipment.system.domain.Purchase;
import com.equipment.system.mapper.PurchaseMapper;
import com.equipment.system.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PurchaseServiceImpl:
 * @author: Yayo
 * @date: 2021/4/18 13:41
 */
@Service
public class PurchaseServiceImpl implements IPurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> findByUserId(Long userId) {
        return purchaseMapper.findByUserId(userId);
    }

    @Override
    public Boolean delete(Long id) {
        return purchaseMapper.delete(id) > 0;
    }

    @Override
    public Boolean create(Purchase purchase) {
        Purchase oldPurchase = purchaseMapper.findByUserIdEquipmentId(purchase);
        if(oldPurchase == null){
            return purchaseMapper.create(purchase) > 0;
        }else{
            return purchaseMapper.update(purchase) > 0;
        }
    }

    @Override
    public List<Purchase> findByIds(List<Long> ids) {
        return purchaseMapper.findByIds(ids);
    }
}
