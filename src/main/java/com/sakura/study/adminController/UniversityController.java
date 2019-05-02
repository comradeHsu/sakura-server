package com.sakura.study.adminController;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.University;
import com.sakura.study.service.UniversityService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    /**
     * 获取分页学校信息
     * @param page
     * @param schoolName
     * @return
     */
    @RequestMapping(value = "/universities",method = RequestMethod.GET)
    public ResponseResult getUniversities(PageRequest page,String schoolName){
        if(schoolName.trim().isEmpty()){
            schoolName = null;
        }
        return universityService.getPageSchools(page,schoolName);
    }

    /**
     * 添加学校信息
     * @param token
     * @param university
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@RequestHeader("Token")String token, @RequestBody University university){
        return null;
    }
}
