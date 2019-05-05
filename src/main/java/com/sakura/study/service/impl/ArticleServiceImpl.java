package com.sakura.study.service.impl;

import com.sakura.study.dao.ArticleMapper;
import com.sakura.study.dto.ArticleDto;
import com.sakura.study.dto.ArticlePageRequest;
import com.sakura.study.model.Article;
import com.sakura.study.model.Employee;
import com.sakura.study.service.ArticleService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.RedisUtil;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    ArticleMapper articleMapper;


    /**
     * 获取文章列表
     *
     * @param pageRequest
     * @return
     */
    @Override
    public ResponseResult getArticles(ArticlePageRequest pageRequest) {
        List<ArticleDto> data = articleMapper.getArticles(pageRequest);
        int dataCount = articleMapper.getArticlesCount(pageRequest);
        return ResponseResult.pageResult(data,dataCount);
    }

    /**
     * 添加文章
     *
     * @param token
     * @param article
     * @return
     */
    @Override
    public Article add(String token, Article article) {
        Employee employee = (Employee) RedisUtil.get(token);
        article.setCreateBy(employee.getId());
        article.setUpdateBy(employee.getId());
        articleMapper.insertSelective(article);
        return article;
    }

    /**
     * 编辑文章
     *
     * @param token
     * @param article
     */
    @Override
    public void edit(String token, Integer id, Article article) {
        getArticleById(id);
        article.setId(id);
        articleMapper.updateByPrimaryKeySelective(article);
    }

    /**
     * 删除文章
     *
     * @param token
     * @param id
     */
    @Override
    public void delete(String token, Integer id) {
        Article article = getArticleById(id);
        article.setDeleted(true);
        articleMapper.updateByPrimaryKeySelective(article);
    }

    /**
     * 发布/取消发布
     *
     * @param token
     * @param id
     * @param status
     * @return
     */
    @Override
    public String operation(String token, Integer id, Integer status) {
        Article article = getArticleById(id);
        article.setStatus(status);
        articleMapper.updateByPrimaryKeySelective(article);
        return status == 1 ? "发布成功" : "取消发布成功";
    }

    /**
     * 没有则抛出异常
     * @param id
     */
    private Article getArticleById(Integer id) {
        Article record = articleMapper.selectByPrimaryKey(id);
        if(record == null || record.getDeleted()) throw new BusinessException(404,"此资讯不存在或已删除");
        return record;
    }
}
