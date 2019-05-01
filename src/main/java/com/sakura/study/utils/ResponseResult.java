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
}
