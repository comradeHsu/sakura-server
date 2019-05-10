package com.sakura.study.controller.apiController;

import com.sakura.study.dto.MajorPageRequest;
import com.sakura.study.service.MajorService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MajorApiController {

    @Autowired
    MajorService majorService;

    /**
     * 获取分页的专业信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/university/{id}/majors",method = RequestMethod.GET)
    public ResponseResult getPageMajors(@PathVariable("id") Integer id, MajorPageRequest request){
        if(StringUtils.isEmpty(request.getMajorName())) {
            request.setMajorName(null);
        }
        if(StringUtils.isEmpty(request.getDegreeType())) {
            request.setDegreeType(null);
        }
        request.initSkip();
        return majorService.getPageMajors(request,id);
    }
}
