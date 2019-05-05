package com.sakura.study.dto;

import com.sakura.study.model.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto extends Article{

    private String createUser;

    private String updateUser;
}
