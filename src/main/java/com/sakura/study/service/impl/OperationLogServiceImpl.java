package com.sakura.study.service.impl;

import com.sakura.study.dao.OperationLogMapper;
import com.sakura.study.dto.OperationLogDto;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.service.OperationLogService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService{

    @Autowired
    OperationLogMapper operationLogMapper;


    /**
     * 分页操作日志
     *
     * @param page
     * @param employeeId
     * @return
     */
    @Override
    public ResponseResult getPageLogs(PageRequest page, Integer employeeId) {
        List<OperationLogDto> data = operationLogMapper.getPageLogs(employeeId,page.getSkip(),page.getPageCount());
        int dataCount = operationLogMapper.getLogsCount(employeeId);
        return ResponseResult.pageResult(data,dataCount);
    }
}
