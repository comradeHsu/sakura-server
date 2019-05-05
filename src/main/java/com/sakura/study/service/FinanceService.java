package com.sakura.study.service;

import com.sakura.study.dto.FinancePageRequest;
import com.sakura.study.model.Finance;
import com.sakura.study.utils.ResponseResult;

public interface FinanceService {
    /**
     * 获取分页的财务信息
     * @param request
     * @return
     */
    ResponseResult getFinances(FinancePageRequest request);

    /**
     * 添加财务信息
     * @param token
     * @param finance
     * @return
     */
    Finance add(String token,Finance finance);

    /**
     * 编辑财务信息
     * @param token
     * @param finance
     */
    void edit(String token, Finance finance);

    /**
     * 删除信息
     * @param token
     * @param id
     */
    void deleted(String token, Integer id);
}
