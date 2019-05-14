package com.sakura.study.controller.apiController;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.UniversityPageRequest;
import com.sakura.study.model.University;
import com.sakura.study.service.UniversityService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/university")
public class SchoolController {

    @Autowired
    UniversityService universityService;

    /**
     * 用户搜索学校列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ResponseResult search(UniversityPageRequest request){
        request.initSkip();
        if(StringUtils.isEmpty(request.getName()))
            request.setName(null);
        return universityService.search(request);
    }

    /**
     * 获取学校信息详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseResult getDetail(@PathVariable("id") Integer id){
        University university = universityService.getUniversityById(id);
        return ResponseResult.success(university);
    }

    /**
     * 获取用户推荐学校
     * @param token
     * @return
     */
    @RequestMapping(value = "/recommend",method = RequestMethod.GET)
    public ResponseResult recommend(@RequestHeader("Token")String token, UniversityPageRequest page){
        page.initSkip();
        return universityService.recommend(token,page);
    }
}
