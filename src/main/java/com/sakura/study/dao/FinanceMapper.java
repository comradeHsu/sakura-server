package com.sakura.study.dao;

import com.sakura.study.dto.FinancePageRequest;
import com.sakura.study.model.Finance;

import java.util.List;

public interface FinanceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Finance record);

    int insertSelective(Finance record);

    Finance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Finance record);

    int updateByPrimaryKey(Finance record);

    /**
     * 获取分页的财务信息
     * @param request
     * @return
     */
    List<Finance> getFinances(FinancePageRequest request);

    /**
     * 获取数量
     * @param request
     * @return
     */
    int getFinancesCount(FinancePageRequest request);
}