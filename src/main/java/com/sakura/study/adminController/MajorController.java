package com.sakura.study.adminController;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Major;
import com.sakura.study.service.MajorService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class MajorController {

    @Autowired
    private MajorService majorService;

    /**
     * 获取学校下的专业信息
     * @param page
     * @param id 学校id
     * @return
     */
    @RequestMapping(value = "/university/{id}/majors",method = RequestMethod.GET)
    public ResponseResult getMajors(PageRequest page, @PathVariable("id") Integer id){
        return majorService.getPageMajors(page,id);
    }

    /**
     * 修改专业信息
     * @param token
     * @param id
     * @param majorId
     * @param major
     * @return
     */
    @RequestMapping(value = "/university/{id}/major/{majorId}",method = RequestMethod.PUT)
    public ResponseResult addMajor(@RequestHeader("Token") String token, @PathVariable("id") Integer id,
                                   @PathVariable("majorId") Integer majorId, @RequestBody Major major){
        if(!Objects.equals(major.getUniversityId(), id)){
            throw new BusinessException(400,"参数错误");
        }
        majorService.editMajor(token,id,majorId,major);
        return ResponseResult.success("修改成功",null);
    }

    /**
     * 新增专业信息
     * @param token
     * @param id
     * @param major
     * @return
     */
    @RequestMapping(value = "/university/{id}/major",method = RequestMethod.POST)
    public ResponseResult addMajor(@RequestHeader("Token") String token, @PathVariable("id") Integer id,
                                   @RequestBody Major major){
        if(!Objects.equals(major.getUniversityId(), id)){
            throw new BusinessException(400,"参数错误");
        }
        majorService.createMajor(token,id,major);
        return ResponseResult.success("创建成功",null);
    }

    /**
     * 删除专业
     * @param token
     * @param id
     * @param majorId
     * @return
     */
    @RequestMapping(value = "/university/{id}/major/{majorId}",method = RequestMethod.DELETE)
    public ResponseResult deleteMajor(@RequestHeader("Token") String token, @PathVariable("id") Integer id,
                                   @PathVariable("majorId") Integer majorId){
        majorService.deleteMajor(token,id,majorId);
        return ResponseResult.success("删除成功",null);
    }
}
