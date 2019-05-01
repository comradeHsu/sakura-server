package com.sakura.study.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException{

    private Integer code;

    private String msg;

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
