package com.sakura.study.adminController;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.service.OperationLogService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/log")
public class OperationLogController {

    @Autowired
    OperationLogService operationLogService;

    /**
     * 操作日志分页
     * @param page
     * @param employeeId
     * @return
     */
    @RequestMapping(value = "/logs",method = RequestMethod.GET)
    public ResponseResult getPageLogs(PageRequest page,Integer employeeId){
        return operationLogService.getPageLogs(page,employeeId);
    }
}
