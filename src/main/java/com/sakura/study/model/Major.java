package com.sakura.study.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Major {
    private Integer id;

    private Integer universityId;

    private String majorName;

    private String degreeType;

    private String majorDesc;

    private Date createTime;

    private Boolean deleted;

}