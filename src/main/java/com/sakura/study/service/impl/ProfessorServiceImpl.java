package com.sakura.study.service.impl;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dao.OperationLogMapper;
import com.sakura.study.dao.ProfessorMapper;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.ProfessorDto;
import com.sakura.study.model.Employee;
import com.sakura.study.model.OperationLog;
import com.sakura.study.model.Professor;
import com.sakura.study.service.ProfessorService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.CopyUtils;
import com.sakura.study.utils.Operation;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    @Autowired
    private ProfessorMapper professorMapper;

    @Resource(name = "employeeCache")
    private LoadingCache<String, Optional<Employee>> employeeCache;

    @Autowired
    private OperationLogMapper operationLogMapper;
    /**
     * 添加教授
     *
     * @param professor
     * @param employee
     * @return
     */
    @Override
    @Transactional
    public Professor add(Professor professor, Employee employee) {
        professorMapper.insertSelective(professor);
        OperationLog operationLog = buildLog(professor,Operation.ADD,employee);
        operationLogMapper.insertSelective(operationLog);
        return professor;
    }

    /**
     * 删除教授
     *
     * @param id
     * @param employee
     */
    @Override
    public void delete(Integer id, Employee employee) {
        Professor professor = getProfessorById(id);
        professor.setDeleted(true);
        professorMapper.updateByPrimaryKeySelective(professor);
        OperationLog operationLog = buildLog(professor,Operation.DELETE,employee);
        operationLogMapper.insertSelective(operationLog);
    }

    /**
     * 修改教授信息
     *
     * @param professor
     * @param employee
     * @return
     */
    @Override
    @Transactional
    public void edit(Professor professor, Employee employee) {
        Professor data = getProfessorById(professor.getId());
        CopyUtils.copyProperties(professor,data);
        professorMapper.updateByPrimaryKeySelective(data);
        OperationLog operationLog = buildLog(data,Operation.EDIT,employee);
        operationLogMapper.insertSelective(operationLog);
    }

    /**
     * 获取教授信息的分页列表
     *
     * @param page
     * @return
     */
    @Override
    public ResponseResult getPage(PageRequest page) {
        List<ProfessorDto> data = professorMapper.getPageProfessor(page);
        int dataCount = professorMapper.getPageProfessorCount();
        return ResponseResult.pageResult(data,dataCount);
    }

    /**
     *获取信息根据id
     * @param id
     * @return
     */
    private Professor getProfessorById(Integer id){
        Professor professor = professorMapper.selectByPrimaryKey(id);
        if(professor == null || professor.getDeleted())
            throw new BusinessException(400,"教授信息不存在");
        return professor;
    }

    private OperationLog buildLog(Professor record, Operation operation, Employee employee){
        OperationLog operationLog = new OperationLog();
        operationLog.setOperation(operation.getValue());
        operationLog.setContent(buildContent(record,operation.getDesc()));
        operationLog.setEmployeeId(employee.getId());
        return operationLog;
    }

    private String buildContent(Professor record, String method){
        return method +"教授"+record.getName();
    }
}
