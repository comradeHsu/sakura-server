package com.sakura.study.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult {

    private int code = 200;

    private String message;

    private Object data;

    private Integer dataCount;

    public static ResponseResult success(Object data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(data);
        return responseResult;
    }

    public static ResponseResult success(String message, Object data){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setMessage(message);
        responseResult.setData(data);
        return responseResult;
    }

    public static ResponseResult pageResult(Object data,int dataCount){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setData(data);
        responseResult.setDataCount(dataCount);
        return responseResult;
    }
}
