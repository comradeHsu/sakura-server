package com.sakura.study.controller.apiController;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dao.LeaveWordMapper;
import com.sakura.study.dto.WordPageRequest;
import com.sakura.study.model.LeaveWord;
import com.sakura.study.model.User;
import com.sakura.study.utils.Assert;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/word")
public class LeaveWordController {

    @Autowired
    LeaveWordMapper leaveWordMapper;

    @Resource(name = "userCache")
    private LoadingCache<String, Optional<User>> userCache;

    /**
     * 新增留言
     * @param token
     * @param word
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@RequestHeader("Token")String token, @RequestBody LeaveWord word){
        User user = userCache.getUnchecked(token).orElse(null);
        Assert.notNull(user,"登录已失效");
        word.setUserId(user.getId());
        leaveWordMapper.insertSelective(word);
        return ResponseResult.success("留言成功",null);
    }

    /**
     * 获取分页的留言数据
     * @param token
     * @param page
     * @return
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseResult getPage(@RequestHeader("Token")String token,WordPageRequest page){
        User user = userCache.getUnchecked(token).orElse(null);
        Assert.notNull(user,"登录已失效");
        page.setUserId(user.getId());
        page.initSkip();
        List<LeaveWord> data = leaveWordMapper.getPage(page);
        int dataCount = leaveWordMapper.getPageCount(page);
        return ResponseResult.pageResult(data,dataCount);
    }
}
