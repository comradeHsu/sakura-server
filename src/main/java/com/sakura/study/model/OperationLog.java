package com.sakura.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OperationLog {

    private Integer id;

    private Integer employeeId;

    private Integer operation;

    private String content;

    private Date createTime;

    private Boolean deleted;
}