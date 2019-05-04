package com.sakura.study.service.impl;

import com.sakura.study.dao.MajorMapper;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Major;
import com.sakura.study.service.MajorService;
import com.sakura.study.service.UniversityService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MajorServiceImpl implements MajorService{

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private UniversityService universityService;

    /**
     * 查找学校的专业信息，分页
     *
     * @param page
     * @param universityId
     * @return
     */
    @Override
    public ResponseResult getPageMajors(PageRequest page, Integer universityId) {
        universityService.getUniversityById(universityId);
        List<Major> data = majorMapper.getPageMajors(universityId,page.getSkip(),page.getPageCount());
        int dataCount = majorMapper.getPageMajorsCount(universityId);
        return ResponseResult.pageResult(data,dataCount);
    }

    /**
     * 修改专业信息
     *
     * @param token
     * @param universityId
     * @param majorId
     * @param major
     */
    @Override
    public void editMajor(String token, Integer universityId, Integer majorId, Major major) {
        universityService.getUniversityById(universityId);
        getMajorById(majorId);
        Major sameName = majorMapper.findByUniversityIdAndName(universityId,major.getMajorName());
        if(sameName != null && !Objects.equals(sameName.getId(), majorId))
            throw new BusinessException(400,"同一个学校的专业名字不能重复");
        major.setId(majorId);
        majorMapper.updateByPrimaryKeySelective(major);
    }

    /**
     * 创建专业
     *
     * @param token
     * @param universityId
     * @param major
     */
    @Override
    public void createMajor(String token, Integer universityId, Major major) {
        universityService.getUniversityById(universityId);
        Major sameName = majorMapper.findByUniversityIdAndName(universityId,major.getMajorName());
        if(sameName != null) throw new BusinessException(400,"同一个学校的专业名字不能重复");
        majorMapper.insertSelective(major);
    }

    /**
     * 删除专业信息
     *
     * @param token
     * @param universityId
     * @param majorId
     */
    @Override
    public void deleteMajor(String token, Integer universityId, Integer majorId) {
        universityService.getUniversityById(universityId);
        Major major = getMajorById(majorId);
        if(major.getUniversityId() != universityId){
            throw new BusinessException(400,"参数错误");
        }
        major.setDeleted(true);
        majorMapper.updateByPrimaryKeySelective(major);
    }

    /**
     * 查找专业
     * @param majorId
     * @return
     */
    private Major getMajorById(Integer majorId){
        Major major = majorMapper.selectByPrimaryKey(majorId);
        if(major == null || major.getDeleted()) throw new BusinessException(404,"专业信息不存在或已删除");
        return major;
    }
}
