package com.sakura.study.service.impl;

import com.sakura.study.dao.UniversityMapper;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.University;
import com.sakura.study.service.UniversityService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityServiceImpl implements UniversityService{

    @Autowired
    private UniversityMapper universityMapper;

    /**
     * 获取分页学校信息
     *
     * @param page
     * @param schoolName
     * @return
     */
    @Override
    public ResponseResult getPageSchools(PageRequest page, String schoolName) {
        List<University> data = universityMapper.getPageSchools(schoolName,page.getSkip(),page.getPageCount());
        int dataCOunt = universityMapper.getPageSchoolsCount(schoolName);
        return ResponseResult.pageResult(data,dataCOunt);
    }
}
