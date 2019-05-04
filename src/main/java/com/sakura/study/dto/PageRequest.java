package com.sakura.study.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {

    private int page = 1;

    private int pageCount = 10;

    private int skip;

    public int getSkip(){
        return (this.page - 1) * this.pageCount;
    }

    public PageRequest(){
        this.skip = getSkip();
    }

}
