package com.sakura.study.service;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.utils.ResponseResult;

public interface OperationLogService {
    /**
     * 分页操作日志
     * @param page
     * @param employeeId
     * @return
     */
    ResponseResult getPageLogs(PageRequest page,Integer employeeId);
}
