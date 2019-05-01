package com.sakura.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Function {
    private Integer id;

    private String moduleName;

    private String moduleRoute;

    private Date createTime;

    private Boolean deleted;
}