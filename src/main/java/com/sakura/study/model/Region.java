package com.sakura.study.model;

public class Region {
    private Integer id;

    private Integer parentId;

    private String japenese;

    private String chinese;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getJapenese() {
        return japenese;
    }

    public void setJapenese(String japenese) {
        this.japenese = japenese == null ? null : japenese.trim();
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese == null ? null : chinese.trim();
    }
}