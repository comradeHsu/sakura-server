package com.sakura.study.dao;

import com.sakura.study.dto.ArticlePageRequest;
import com.sakura.study.model.Article;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 获取文章分页列表
     * @param page
     * @return
     */
    List<Article> getArticles(ArticlePageRequest page);

    /**
     * 获取文章数量
     * @param page
     * @return
     */
    int getArticlesCount(ArticlePageRequest page);
}