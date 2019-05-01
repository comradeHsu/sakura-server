package com.sakura.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Employee {
    private Integer id;

    private Integer departmentId;

    private String username;

    private String password;

    private String phoneNumber;

    private String realName;

    private Date createTime;

    private Boolean deleted;
}