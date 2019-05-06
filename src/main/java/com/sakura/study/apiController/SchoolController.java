package com.sakura.study.apiController;

import com.sakura.study.dto.UniversityPageRequest;
import com.sakura.study.service.UniversityService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return null;
    }
}
