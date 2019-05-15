package com.sakura.study.model;

import java.util.Date;

public class UserAgreement {
    private Integer id;

    private Integer userId;

    private String apply;

    private String train;

    private String visa;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply == null ? null : apply.trim();
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train == null ? null : train.trim();
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa == null ? null : visa.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}