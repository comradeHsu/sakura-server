package com.sakura.study.controller.adminController;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dao.CommunicationRecordMapper;
import com.sakura.study.dto.ArticlePageRequest;
import com.sakura.study.dto.CommunicationRequest;
import com.sakura.study.model.Article;
import com.sakura.study.model.CommunicationRecord;
import com.sakura.study.model.Employee;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/communication")
public class CommunicationController {
    @Autowired
    CommunicationRecordMapper communicationRecordMapper;
    @Resource(name = "employeeCache")
    private LoadingCache<String, Optional<Employee>> employeeCache;

    /**
     * 保存聊天数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseResult saveRecord( @RequestBody CommunicationRecord request) {
        request.setCreateTime(new Date());
        communicationRecordMapper.insert(request);
        return ResponseResult.success("添加成功",null);
    }

    /**
     * 获取分页的聊天数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseResult getPageArticles(CommunicationRequest request){
        //request.setStatus(1);
        request.initSkip();
        return ResponseResult.pageResult(
                communicationRecordMapper.getPages(request),
                communicationRecordMapper.count(request));

    }
}
