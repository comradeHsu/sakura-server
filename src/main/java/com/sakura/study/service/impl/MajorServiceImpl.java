package com.sakura.study.service.impl;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dao.MajorMapper;
import com.sakura.study.dao.OperationLogMapper;
import com.sakura.study.dto.MajorPageRequest;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Employee;
import com.sakura.study.model.Major;
import com.sakura.study.model.OperationLog;
import com.sakura.study.model.User;
import com.sakura.study.service.MajorService;
import com.sakura.study.service.UniversityService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.Operation;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MajorServiceImpl implements MajorService{

    @Autowired
    private MajorMapper majorMapper;

    @Autowired
    private UniversityService universityService;

    @Resource(name = "employeeCache")
    private LoadingCache<String, Optional<Employee>> employeeCache;

    @Autowired
    private OperationLogMapper operationLogMapper;

    /**
     * 查找学校的专业信息，分页
     *
     * @param page
     * @param universityId
     * @return
     */
    @Override
    public ResponseResult getPageMajors(MajorPageRequest page, Integer universityId) {
        universityService.getUniversityById(universityId);
        page.setUniversityId(universityId);
        List<Major> data = majorMapper.getPageMajors(page);
        int dataCount = majorMapper.getPageMajorsCount(page);
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
    @Transactional
    public void editMajor(String token, Integer universityId, Integer majorId, Major major) {
        universityService.getUniversityById(universityId);
        Major record = getMajorById(majorId);
        Major sameName = majorMapper.findByUniversityIdAndName(universityId,major.getMajorName());
        if(sameName != null && !Objects.equals(sameName.getId(), majorId))
            throw new BusinessException(400,"同一个学校的专业名字不能重复");
        major.setId(majorId);
        majorMapper.updateByPrimaryKeySelective(major);
        Employee self = employeeCache.getUnchecked(token).orElse(null);
        OperationLog operationLog = buildLog(record,Operation.EDIT,self);
        operationLogMapper.insertSelective(operationLog);
    }

    /**
     * 创建专业
     *
     * @param token
     * @param universityId
     * @param major
     */
    @Override
    @Transactional
    public void createMajor(String token, Integer universityId, Major major) {
        universityService.getUniversityById(universityId);
        Major sameName = majorMapper.findByUniversityIdAndName(universityId,major.getMajorName());
        if(sameName != null) throw new BusinessException(400,"同一个学校的专业名字不能重复");
        majorMapper.insertSelective(major);
        Employee self = employeeCache.getUnchecked(token).orElse(null);
        OperationLog operationLog = buildLog(major,Operation.ADD,self);
        operationLogMapper.insertSelective(operationLog);
    }

    /**
     * 删除专业信息
     *
     * @param token
     * @param universityId
     * @param majorId
     */
    @Override
    @Transactional
    public void deleteMajor(String token, Integer universityId, Integer majorId) {
        universityService.getUniversityById(universityId);
        Major major = getMajorById(majorId);
        if(major.getUniversityId() != universityId){
            throw new BusinessException(400,"参数错误");
        }
        major.setDeleted(true);
        majorMapper.updateByPrimaryKeySelective(major);
        Employee self = employeeCache.getUnchecked(token).orElse(null);
        OperationLog operationLog = buildLog(major,Operation.DELETE,self);
        operationLogMapper.insertSelective(operationLog);
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

    private OperationLog buildLog(Major record, Operation operation, Employee employee){
        OperationLog operationLog = new OperationLog();
        operationLog.setOperation(operation.getValue());
        operationLog.setContent(buildContent(record,operation.getDesc()));
        operationLog.setEmployeeId(employee.getId());
        return operationLog;
    }

    private String buildContent(Major record, String method){
        return method +"专业"+record.getMajorName();
    }
}
