package com.equipment.system.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.equipment.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.equipment.system.mapper.BorrowMapper;
import com.equipment.system.domain.Borrow;
import com.equipment.system.service.IBorrowService;
import com.equipment.common.core.text.Convert;

/**
 * 借用管理Service业务层处理
 * 
 * @author equipment
 * @date 2021-02-28
 */
@Service
public class BorrowServiceImpl implements IBorrowService 
{
    @Autowired
    private BorrowMapper borrowMapper;

    /**
     * 查询借用管理
     * 
     * @param id 借用管理ID
     * @return 借用管理
     */
    @Override
    public Borrow selectBorrowById(Long id)
    {
        return borrowMapper.selectBorrowById(id);
    }

    /**
     * 查询借用管理列表
     * 
     * @param borrow 借用管理
     * @return 借用管理
     */
    @Override
    public List<Borrow> selectBorrowList(Borrow borrow)
    {
        return borrowMapper.selectBorrowList(borrow);
    }

    /**
     * 新增借用管理
     * 
     * @param borrow 借用管理
     * @return 结果
     */
    @Override
    public int insertBorrow(Borrow borrow)
    {
        return borrowMapper.insertBorrow(borrow);
    }

    /**
     * 修改借用管理
     * 
     * @param borrow 借用管理
     * @return 结果
     */
    @Override
    public int updateBorrow(Borrow borrow)
    {
        return borrowMapper.updateBorrow(borrow);
    }

    /**
     * 删除借用管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBorrowByIds(String ids)
    {
        return borrowMapper.deleteBorrowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除借用管理信息
     * 
     * @param id 借用管理ID
     * @return 结果
     */
    @Override
    public int deleteBorrowById(Long id)
    {
        return borrowMapper.deleteBorrowById(id);
    }

    @Override
    public List<Borrow> selectOverdueList(Borrow borrow) {
        return borrowMapper.selectOverdueList(borrow);
    }

    @Override
    public List<Borrow> selectRefuseList(Borrow borrow) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            borrow.setReturnAt(sdf.parse(sdf.format(new Date())));
        } catch (ParseException e) {
        }
        return borrowMapper.selectRefunsList(borrow);
    }
}
