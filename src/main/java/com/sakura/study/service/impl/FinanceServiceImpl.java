package com.sakura.study.service.impl;

import com.sakura.study.dao.FinanceMapper;
import com.sakura.study.dto.FinancePageRequest;
import com.sakura.study.model.Finance;
import com.sakura.study.model.University;
import com.sakura.study.service.FinanceService;
import com.sakura.study.utils.Assert;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService{

    @Autowired
    FinanceMapper financeMapper;


    /**
     * 获取分页的财务信息
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult getFinances(FinancePageRequest request) {
        List<Finance> data = financeMapper.getFinances(request);
        int dataCount = financeMapper.getFinancesCount(request);
        return ResponseResult.pageResult(data,dataCount);
    }

    /**
     * 添加财务信息
     *
     * @param token
     * @param finance
     * @return
     */
    @Override
    public Finance add(String token, Finance finance) {
        financeMapper.insertSelective(finance);
        return finance;
    }

    /**
     * 编辑财务信息
     *
     * @param token
     * @param finance
     */
    @Override
    public void edit(String token, Finance finance) {
        getFinanceById(finance.getId());
        financeMapper.updateByPrimaryKeySelective(finance);
    }

    /**
     * 删除信息
     *
     * @param token
     * @param id
     */
    @Override
    public void deleted(String token, Integer id) {
        Finance finance = getFinanceById(id);
        finance.setDeleted(true);
        financeMapper.updateByPrimaryKeySelective(finance);
    }

    /**
     * 查找信息
     * @param id
     * @return
     */
    private Finance getFinanceById(Integer id){
        Finance finance = financeMapper.selectByPrimaryKey(id);
        if(finance == null || finance.getDeleted()) throw new BusinessException(404,"学校信息不存在或已删除");
        return finance;
    }
}
