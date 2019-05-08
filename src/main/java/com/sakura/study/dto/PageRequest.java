package com.sakura.study.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {

    private int page = 1;

    private int pageCount = 10;

    private int skip;

    public void initSkip(){
        this.skip = (this.page - 1) * this.pageCount;
    }

    public PageRequest(){
        initSkip();
    }

}
