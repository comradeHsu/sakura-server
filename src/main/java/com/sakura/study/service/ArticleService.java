package com.sakura.study.service;

import com.sakura.study.dto.ArticlePageRequest;
import com.sakura.study.model.Article;
import com.sakura.study.utils.ResponseResult;

public interface ArticleService {
    /**
     * 获取文章列表
     * @param pageRequest
     * @return
     */
    ResponseResult getArticles(ArticlePageRequest pageRequest);

    /**
     * 添加文章
     * @param token
     * @param article
     * @return
     */
    Article add(String token,Article article);

    /**
     * 编辑文章
     * @param token
     * @param article
     */
    void edit(String token,Integer id, Article article);

    /**
     * 删除文章
     * @param token
     * @param id
     */
    void delete(String token,Integer id);

    /**
     * 发布/取消发布
     * @param token
     * @param id
     * @param status
     * @return
     */
    String operation(String token,Integer id, Integer status);

    /**
     * api
     * 获取文章详情
     * @param id
     * @return
     */
    Article getById(Integer id);
}
