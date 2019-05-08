package com.sakura.study.controller.apiController;

import com.sakura.study.dto.ArticlePageRequest;
import com.sakura.study.model.Article;
import com.sakura.study.service.ArticleService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
public class InformationController {

    @Autowired
    ArticleService articleService;

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseResult getArticle(@PathVariable("id") Integer id){
        Article article = articleService.getById(id);
        return ResponseResult.success(article);
    }

    /**
     * 获取分页的文章列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/articles",method = RequestMethod.GET)
    public ResponseResult getPageArticles(ArticlePageRequest request){
        request.setStatus(1);
        request.initSkip();
        return articleService.getArticles(request);
    }
}
