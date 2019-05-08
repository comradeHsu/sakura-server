package com.sakura.study.controller.adminController;

import com.sakura.study.dto.ArticlePageRequest;
import com.sakura.study.model.Article;
import com.sakura.study.service.ArticleService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     *获取文章列表
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = "/articles",method = RequestMethod.GET)
    public ResponseResult articles(ArticlePageRequest pageRequest){
        pageRequest.initSkip();
        return articleService.getArticles(pageRequest);
    }

    /**
     * 创建文章
     * @param token
     * @param article
     * @return
     */
    @RequestMapping(value = "/article",method = RequestMethod.POST)
    public ResponseResult add(@RequestHeader("Token") String token, @RequestBody Article article){
        Article record = articleService.add(token,article);
        return ResponseResult.success(record);
    }

    /**
     * 编辑文章
     * @param token
     * @param article
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseResult edit(@RequestHeader("Token") String token, @RequestBody Article article,
                               @PathVariable("id") Integer id){
        articleService.edit(token,id,article);
        return ResponseResult.success("修改成功",null);
    }

    /**
     * 删除文章
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@RequestHeader("Token") String token, @PathVariable("id") Integer id){
        articleService.delete(token,id);
        return ResponseResult.success("删除成功",null);
    }

    /**
     * 发布/取消发布操作
     * @param token
     * @param article
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    public ResponseResult operation(@RequestHeader("Token") String token, @RequestBody Article article,
                                    @PathVariable("id") Integer id){
        if(article.getStatus() == null) throw new BusinessException(400,"参数错误");
        if(article.getStatus() != 1 && article.getStatus() != 0) throw new BusinessException(400,"参数错误");
        String message = articleService.operation(token,id,article.getStatus());
        return ResponseResult.success(message,null);
    }
}
