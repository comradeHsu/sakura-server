package com.sakura.study.model;

import java.util.Date;

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

    private Integer scoreTop;

    private Integer scoreButtom;

    private String description;

    private Date createTime;

    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public String getSchoolIcon() {
        return schoolIcon;
    }

    public void setSchoolIcon(String schoolIcon) {
        this.schoolIcon = schoolIcon == null ? null : schoolIcon.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public Integer getWorldRanking() {
        return worldRanking;
    }

    public void setWorldRanking(Integer worldRanking) {
        this.worldRanking = worldRanking;
    }

    public Integer getNationalRanking() {
        return nationalRanking;
    }

    public void setNationalRanking(Integer nationalRanking) {
        this.nationalRanking = nationalRanking;
    }

    public Integer getScoreTop() {
        return scoreTop;
    }

    public void setScoreTop(Integer scoreTop) {
        this.scoreTop = scoreTop;
    }

    public Integer getScoreButtom() {
        return scoreButtom;
    }

    public void setScoreButtom(Integer scoreButtom) {
        this.scoreButtom = scoreButtom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}