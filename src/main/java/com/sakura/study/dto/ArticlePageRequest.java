package com.sakura.study.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticlePageRequest extends PageRequest{

    private String title;

    private Integer status;

}
