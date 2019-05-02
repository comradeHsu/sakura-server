package com.sakura.study.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {

    private int page = 1;

    private int pageCount = 10;

    public int getSkip(){
        return (this.page - 1) * this.pageCount;
    }

}
