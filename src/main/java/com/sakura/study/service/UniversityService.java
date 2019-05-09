package com.sakura.study.service;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.UniversityPageRequest;
import com.sakura.study.model.University;
import com.sakura.study.utils.ResponseResult;

public interface UniversityService {
    /**
     * 获取分页学校信息
     * @param page
     * @param schoolName
     * @return
     */
    ResponseResult getPageSchools(PageRequest page,String schoolName);

    /**
     * 添加学校
     * @param token
     * @param university
     */
    void addUniversity(String token, University university);

    /**
     * 修改学校信息
     * @param token
     * @param university
     * @param id
     */
    void editUniversity(String token, University university,Integer id);

    /**
     * 删除学校信息
     * @param token
     * @param id
     */
    void deleteUniversity(String token,Integer id);

    /**
     * 查找学校
     * @param id
     * @return
     */
    University getUniversityById(Integer id);

    /**
     * api
     * 学校搜索
     * @param request
     * @return
     */
    ResponseResult search(UniversityPageRequest request);
}
