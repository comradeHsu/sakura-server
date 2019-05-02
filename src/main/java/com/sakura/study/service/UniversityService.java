package com.sakura.study.service;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.utils.ResponseResult;

public interface UniversityService {
    /**
     * 获取分页学校信息
     * @param page
     * @param schoolName
     * @return
     */
    ResponseResult getPageSchools(PageRequest page,String schoolName);
}
