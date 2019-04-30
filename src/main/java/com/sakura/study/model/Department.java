package com.sakura.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Department {
    private Integer id;

    private String department;

    private String description;

    private Date createTime;

    private Boolean deleted;

}