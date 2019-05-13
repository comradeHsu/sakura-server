package com.sakura.study.model;

import java.util.Date;

public class Assessment {
    private Integer id;

    private Integer userId;

    private String school;

    private Integer schoolType;

    private String major;

    private Integer toefl;

    private Integer japaneseLevel;

    private Integer schoolGpa;

    private Integer gpa;

    private Integer score;

    private Integer totalScore;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public Integer getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Integer schoolType) {
        this.schoolType = schoolType;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public Integer getToefl() {
        return toefl;
    }

    public void setToefl(Integer toefl) {
        this.toefl = toefl;
    }

    public Integer getJapaneseLevel() {
        return japaneseLevel;
    }

    public void setJapaneseLevel(Integer japaneseLevel) {
        this.japaneseLevel = japaneseLevel;
    }

    public Integer getSchoolGpa() {
        return schoolGpa;
    }

    public void setSchoolGpa(Integer schoolGpa) {
        this.schoolGpa = schoolGpa;
    }

    public Integer getGpa() {
        return gpa;
    }

    public void setGpa(Integer gpa) {
        this.gpa = gpa;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}