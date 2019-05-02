package com.sakura.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DepartmentFunction {
    private Integer id;

    private Integer departmentId;

    private Integer functionId;

    private Date createTime;

    private Boolean deleted;

}