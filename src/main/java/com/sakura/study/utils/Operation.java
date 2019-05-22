package com.sakura.study.utils;

public enum Operation {

    ADD(1,"新增"),
    EDIT(2,"修改"),
    DELETE(3,"删除");

    private Integer value;

    private String desc;

    Operation(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
