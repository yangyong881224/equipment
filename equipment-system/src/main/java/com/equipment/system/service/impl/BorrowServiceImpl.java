package com.equipment.system.service.impl;

import com.equipment.common.core.text.Convert;
import com.equipment.common.utils.DateUtils;
import com.equipment.system.domain.Borrow;
import com.equipment.system.mapper.BorrowMapper;
import com.equipment.system.service.IBorrowService;
import com.equipment.system.vo.BorrowAndExamineVO;
import com.equipment.system.vo.FavoriteEquipmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            borrow.setReturnAt(sdf.parse(sdf.format(new Date())));
        } catch (ParseException e) {
        }
        return borrowMapper.selectOverdueList(borrow);
    }

    @Override
    public List<Borrow> selectRefuseList(Borrow borrow) {
        return borrowMapper.selectRefunsList(borrow);
    }

    @Override
    public Map<String, String[]> borrowAndExamineStatistics() {
        Date firstDayOfWeek = DateUtils.getFirstDayOfWeek(new Date());
        Map<String,Object> params = new HashMap<>();
        params.put("firstDayOfWeek",firstDayOfWeek);
        List<BorrowAndExamineVO> borrowList = borrowMapper.borrowWeekCount(params);
        List<BorrowAndExamineVO> agreeList = borrowMapper.agreeWeekCount(params);
        List<BorrowAndExamineVO> refuseList = borrowMapper.refuseWeekCount(params);
        String[] weeks = new String[7];
        String[] agrees = new String[7];
        String[] borrows = new String[7];
        String[] refuses = new String[7];
        for(int i = 0 ; i < 7 ; i ++){
            weeks[i] = DateUtils.getCNDaysOfWeek(i);
            Date date = DateUtils.addDays(firstDayOfWeek,i);
            BorrowAndExamineVO borrowDTO = borrowList.stream().filter(borrow -> date.equals(borrow.getGroupDate())).findFirst().orElse(new BorrowAndExamineVO());
            BorrowAndExamineVO agreeDTO = agreeList.stream().filter(agree -> date.equals(agree.getGroupDate())).findFirst().orElse(new BorrowAndExamineVO());
            BorrowAndExamineVO refuseDTO = refuseList.stream().filter(refuse -> date.equals(refuse.getGroupDate())).findFirst().orElse(new BorrowAndExamineVO());

            borrows[i] = borrowDTO.getBorrowCount()==null?"0":borrowDTO.getBorrowCount().toString();
            agrees[i] = agreeDTO.getAgreeCount()==null?"0":agreeDTO.getAgreeCount().toString();
            refuses[i] = refuseDTO.getRefuseCount()==null?"0":refuseDTO.getRefuseCount().toString();
        }
        Map<String, String[]> resultMap = new HashMap<>();
        resultMap.put("weeks", weeks);
        resultMap.put("borrows", borrows);
        resultMap.put("agrees",agrees);
        resultMap.put("refuses",refuses);
        return resultMap;
    }

    @Override
    public Map<String, Object> favoriteEquipment() {
        Map<String, Object> params = new HashMap<>();
        params.put("firstDayOfWeek", DateUtils.getFirstDayOfWeek(new Date()));
        List<FavoriteEquipmentVO> favoriteEquipmentVOList = borrowMapper.favoriteEquipment(params);
        String[] legendData = new String[favoriteEquipmentVOList.size()];
        for(int i = 0 ; i < favoriteEquipmentVOList.size(); i ++){
            legendData[i] = favoriteEquipmentVOList.get(i).getName();
        }
        Map<String, Object> result = new HashMap<>();
        result.put("legendData", legendData);
        result.put("seriesData", favoriteEquipmentVOList);
        return result;
    }

    @Override
    public Map<String, Object> returnStatistics() {
        Map<String, Object> params = new HashMap<>();
        Date firstDayOfWeek = DateUtils.getFirstDayOfWeek(new Date());
        params.put("firstDayOfWeek", firstDayOfWeek);
        List<BorrowAndExamineVO> inTimeReturnList = borrowMapper.inTimeReturn(params);
        List<BorrowAndExamineVO> overTimeReturnList = borrowMapper.overTimeReturn(params);
        String[] weeks = new String[7];
        Long[] inTimeReturns = new Long[7];
        Long[] overTimeReturns = new Long[7];
        for(int i = 0 ; i < 7 ; i ++){
            weeks[i] = DateUtils.getCNDaysOfWeek(i);
            Date date = DateUtils.addDays(firstDayOfWeek,i);
            BorrowAndExamineVO inTimeVO = inTimeReturnList.stream().filter(inTime -> date.equals(inTime.getGroupDate())).findFirst().orElse(new BorrowAndExamineVO());
            BorrowAndExamineVO overTimeVO = overTimeReturnList.stream().filter(overTime -> date.equals(overTime.getGroupDate())).findFirst().orElse(new BorrowAndExamineVO());

            inTimeReturns[i] = inTimeVO.getBorrowCount()==null?0:inTimeVO.getBorrowCount();
            overTimeReturns[i] = overTimeVO.getBorrowCount()==null?0:overTimeVO.getBorrowCount();

        }
        Map<String, Object> result = new HashMap<>();
        result.put("weeks", weeks);
        result.put("inTimeReturns", inTimeReturns);
        result.put("overTimeReturns", overTimeReturns);
        return result;
    }

    @Override
    public List<Borrow> selectUnBack(Borrow borrow) {
        return borrowMapper.selectUnBack(borrow);
    }
}
