package com.sakura.study.controller.adminController;

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
        if(schoolName != null && schoolName.trim().isEmpty()){
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
        universityService.addUniversity(token,university);
        return ResponseResult.success("添加成功",null);
    }

    /**
     * 修改学校信息
     * @param token
     * @param university
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseResult add(@RequestHeader("Token") String token, @RequestBody University university,
                              @PathVariable("id") Integer id){
        universityService.editUniversity(token,university,id);
        return ResponseResult.success("修改成功",null);
    }

    /**
     * 删除学校信息
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@RequestHeader("Token") String token, @PathVariable("id") Integer id){
        universityService.deleteUniversity(token,id);
        return ResponseResult.success("删除成功",null);
    }
}
