package com.sakura.study.config;

import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionCatchHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseResult exceptionHandler(BusinessException be){
        ResponseResult result = new ResponseResult();
        result.setCode(be.getCode());
        result.setMessage(be.getMsg());
        return result;
    }
}
