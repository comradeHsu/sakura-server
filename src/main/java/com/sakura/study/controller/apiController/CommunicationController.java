package com.sakura.study.controller.apiController;

import com.sakura.study.dao.CommunicationRecordMapper;
import com.sakura.study.dto.ArticlePageRequest;
import com.sakura.study.dto.CommunicationRequest;
import com.sakura.study.model.Article;
import com.sakura.study.model.CommunicationRecord;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/communication")
public class CommunicationController {
    @Autowired
    CommunicationRecordMapper communicationRecordMapper;

    /**
     * 保存聊天数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseResult saveRecord(CommunicationRecord request) {
        communicationRecordMapper.insert(request);
        return ResponseResult.success("添加成功",null);
    }

    /**
     * 获取分页的聊天数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/articles",method = RequestMethod.GET)
    public ResponseResult getPageArticles(CommunicationRequest request){
        //request.setStatus(1);
        request.initSkip();
        return ResponseResult.success(request);
    }
}
