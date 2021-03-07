package com.equipment.system.mapper;

import java.util.List;
import com.equipment.system.domain.Borrow;

/**
 * 借用管理Mapper接口
 * 
 * @author equipment
 * @date 2021-02-28
 */
public interface BorrowMapper 
{
    /**
     * 查询借用管理
     * 
     * @param id 借用管理ID
     * @return 借用管理
     */
    public Borrow selectBorrowById(Long id);

    /**
     * 查询借用管理列表
     * 
     * @param borrow 借用管理
     * @return 借用管理集合
     */
    public List<Borrow> selectBorrowList(Borrow borrow);

    /**
     * 新增借用管理
     * 
     * @param borrow 借用管理
     * @return 结果
     */
    public int insertBorrow(Borrow borrow);

    /**
     * 修改借用管理
     * 
     * @param borrow 借用管理
     * @return 结果
     */
    public int updateBorrow(Borrow borrow);

    /**
     * 删除借用管理
     * 
     * @param id 借用管理ID
     * @return 结果
     */
    public int deleteBorrowById(Long id);

    /**
     * 批量删除借用管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBorrowByIds(String[] ids);
}
