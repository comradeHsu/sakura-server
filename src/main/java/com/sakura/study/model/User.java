package com.sakura.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {

    public static final User empty = new User();

    private Integer id;

    private Integer parentId;

    private String username;

    private String password;

    private String phoneNumber;

    private Integer age;

    private String icon;

    private String realName;

    private Date createTime;

    private Boolean deleted;
}