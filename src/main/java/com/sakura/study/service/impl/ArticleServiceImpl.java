package com.sakura.study.service.impl;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dao.ArticleMapper;
import com.sakura.study.dao.OperationLogMapper;
import com.sakura.study.dto.ArticleDto;
import com.sakura.study.dto.ArticlePageRequest;
import com.sakura.study.model.Article;
import com.sakura.study.model.Employee;
import com.sakura.study.model.OperationLog;
import com.sakura.study.service.ArticleService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.Operation;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Resource(name = "employeeCache")
    private LoadingCache<String, Optional<Employee>> employeeCache;

    @Autowired
    private OperationLogMapper operationLogMapper;

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
    @Transactional
    public Article add(String token, Article article) {
        Employee employee = employeeCache.getUnchecked(token).orElse(null);
        article.setCreateBy(employee.getId());
        article.setUpdateBy(employee.getId());
        articleMapper.insertSelective(article);
        OperationLog operationLog = buildLog(article,Operation.ADD,employee);
        operationLogMapper.insertSelective(operationLog);
        return article;
    }

    /**
     * 编辑文章
     *
     * @param token
     * @param article
     */
    @Override
    @Transactional
    public void edit(String token, Integer id, Article article) {
        Article article1 = getArticleById(id);
        Employee employee = employeeCache.getUnchecked(token).orElse(null);
        article.setId(id);
        articleMapper.updateByPrimaryKeySelective(article);
        OperationLog operationLog = buildLog(article1,Operation.EDIT,employee);
        operationLogMapper.insertSelective(operationLog);
    }

    /**
     * 删除文章
     *
     * @param token
     * @param id
     */
    @Override
    @Transactional
    public void delete(String token, Integer id) {
        Article article = getArticleById(id);
        Employee employee = employeeCache.getUnchecked(token).orElse(null);
        article.setDeleted(true);
        articleMapper.updateByPrimaryKeySelective(article);
        OperationLog operationLog = buildLog(article,Operation.DELETE,employee);
        operationLogMapper.insertSelective(operationLog);
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
     * api
     * 获取文章详情
     *
     * @param id
     * @return
     */
    @Override
    public Article getById(Integer id) {
        Article article = getArticleById(id);
        if(article.getStatus() == 0) throw new BusinessException(404,"此资讯不存在");
        article.setReadCount(article.getReadCount()+1);
        articleMapper.updateByPrimaryKeySelective(article);
        return article;
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

    private OperationLog buildLog(Article article, Operation operation, Employee employee){
        OperationLog operationLog = new OperationLog();
        operationLog.setOperation(operation.getValue());
        operationLog.setContent(buildContent(article,operation.getDesc()));
        operationLog.setEmployeeId(employee.getId());
        return operationLog;
    }

    private String buildContent(Article article, String method){
        return method + "资讯"+article.getTitle();
    }
}
