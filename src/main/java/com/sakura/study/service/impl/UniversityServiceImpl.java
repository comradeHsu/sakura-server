package com.sakura.study.service.impl;

import com.sakura.study.dao.RegionMapper;
import com.sakura.study.dao.UniversityMapper;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.UniversityPageRequest;
import com.sakura.study.model.Region;
import com.sakura.study.model.University;
import com.sakura.study.service.UniversityService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService{

    @Autowired
    private UniversityMapper universityMapper;

    @Autowired
    private RegionMapper regionMapper;

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
        int dataCount = universityMapper.getPageSchoolsCount(schoolName);
        return ResponseResult.pageResult(data,dataCount);
    }

    /**
     * 添加学校
     *
     * @param token
     * @param university
     */
    @Override
    public void addUniversity(String token, University university) {
        University unirecord = universityMapper.findBySchoolName(university.getSchoolName());
        if(unirecord != null) throw new BusinessException(400,"学校名称不能重复");
        universityMapper.insertSelective(university);
    }

    /**
     * 修改学校信息
     *
     * @param token
     * @param university
     * @param id
     */
    @Override
    public void editUniversity(String token, University university, Integer id) {
        getUniversityById(id);
        University unirecord = universityMapper.findBySchoolName(university.getSchoolName());
        if(unirecord != null && !Objects.equals(unirecord.getId(), id)) throw new BusinessException(400,"学校名称不能重复");
        universityMapper.updateByPrimaryKeySelective(university);
    }

    /**
     * 删除学校信息
     *
     * @param token
     * @param id
     */
    @Override
    public void deleteUniversity(String token, Integer id) {
        University university = getUniversityById(id);
        university.setDeleted(true);
        universityMapper.updateByPrimaryKeySelective(university);
    }

    /**
     * 查找学校
     * @param id
     * @return
     */
    public University getUniversityById(Integer id){
        University university = universityMapper.selectByPrimaryKey(id);
        if(university == null || university.getDeleted()) throw new BusinessException(404,"学校信息不存在或已删除");
        return university;
    }

    /**
     * api
     * 学校搜索
     *
     * @param request
     * @return
     */
    @Override
    public ResponseResult search(UniversityPageRequest request) {
        if(request.getParentId() != null){
            Region region = Optional.ofNullable(regionMapper.selectByPrimaryKey(request.getParentId())).orElse(new Region());
            request.setProvince(region.getAddress());
        }
        if(request.getSubId() != null){
            Region region = Optional.ofNullable(regionMapper.selectByPrimaryKey(request.getSubId())).orElse(new Region());
            request.setCity(region.getAddress());
        }
        List<University> data = universityMapper.search(request);
        int dataCount = universityMapper.searchCount(request);
        return ResponseResult.pageResult(data,dataCount);
    }
}
