package com.sakura.study.service;

import com.sakura.study.dto.MajorPageRequest;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Major;
import com.sakura.study.utils.ResponseResult;

public interface MajorService {
    /**
     * 查找学校的专业信息，分页
     * @param page
     * @param universityId
     * @return
     */
    ResponseResult getPageMajors(MajorPageRequest page, Integer universityId);

    /**
     * 修改专业信息
     * @param token
     * @param universityId
     * @param majorId
     * @param major
     */
    void editMajor(String token, Integer universityId, Integer majorId, Major major);

    /**
     * 创建专业
     * @param token
     * @param universityId
     * @param major
     */
    void createMajor(String token, Integer universityId, Major major);

    /**
     * 删除专业信息
     * @param token
     * @param universityId
     * @param majorId
     */
    void deleteMajor(String token, Integer universityId,Integer majorId);
}
