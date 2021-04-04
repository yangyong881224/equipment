package com.equipment.system.service;

import com.equipment.system.domain.Borrow;

import java.util.List;
import java.util.Map;

/**
 * 借用管理Service接口
 * 
 * @author equipment
 * @date 2021-02-28
 */
public interface IBorrowService 
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
     * 批量删除借用管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBorrowByIds(String ids);

    /**
     * 删除借用管理信息
     * 
     * @param id 借用管理ID
     * @return 结果
     */
    public int deleteBorrowById(Long id);

    List<Borrow> selectOverdueList(Borrow borrow);

    List<Borrow> selectRefuseList(Borrow borrow);

    Map<String, String[]> borrowAndExamineStatistics();

    Map<String,Object> favoriteEquipment();

    Map<String,Object> returnStatistics();

}
