package com.sakura.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class University {
    private Integer id;

    private String schoolName;

    private String englishName;

    private String schoolIcon;

    private String province;

    private String city;

    private String address;

    private String website;

    private Integer worldRanking;

    private Integer nationalRanking;

    private String description;

    private Date createTime;

    private Boolean deleted;
}