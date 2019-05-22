package com.sakura.study.service.impl;

import com.sakura.study.dao.DepartmentFunctionMapper;
import com.sakura.study.dao.DepartmentMapper;
import com.sakura.study.dto.DepartmentDto;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Department;
import com.sakura.study.model.DepartmentFunction;
import com.sakura.study.model.Function;
import com.sakura.study.service.DepartmentService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    DepartmentFunctionMapper departmentFunctionMapper;

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    /**
     * 添加部门
     *
     * @param department
     */
    @Override
    public void add(DepartmentDto department) {
        Department record = departmentMapper.findByName(department.getDepartment());
        if(record != null) throw new BusinessException(400,"部门名称已存在");
        departmentMapper.insertSelective(department);
        if(!CollectionUtils.isEmpty(department.getFunctions())){
            for(Function function : department.getFunctions()){
                DepartmentFunction df = new DepartmentFunction();
                df.setDepartmentId(department.getId());
                df.setFunctionId(function.getId());
                departmentFunctionMapper.insertSelective(df);
            }
        }
    }

    /**
     * 删除部门
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        Department department1 = getDepartment(id);
        int employeeCount = departmentMapper.getDepartmentEmployeeCount(id);
        if(employeeCount > 0) throw new BusinessException(400,"该部门有归属员工无法删除");
        departmentMapper.deleteById(id);
    }

    /**获取分页的部门信息
     * @param page
     * @return
     */
    @Override
    public ResponseResult getPageDepartment(PageRequest page) {
        List<DepartmentDto> data =  departmentMapper.getPageDepartment(page.getSkip(),page.getPageCount());
        int dataCount = departmentMapper.getDepartmentCount();
        return ResponseResult.pageResult(data,dataCount);
    }

    /**
     * 获取所有的部门信息
     *
     * @return
     */
    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment();
    }

    /**
     * 修改部门信息和权限
     *
     * @param data
     * @param id
     */
    @Override
    @Transactional
    public void edit(DepartmentDto data, Integer id) {
        getDepartment(id);
        Department record = departmentMapper.findByName(data.getDepartment());
        if(record != null && !Objects.equals(record.getId(), id)) throw new BusinessException(400,"部门名称已存在");
        data.setId(id);
        departmentMapper.updateByPrimaryKeySelective(data);
        List<Function> functions = data.getFunctions();
        int delCount = departmentFunctionMapper.deleteByDepartmentId(id);
        logger.info("删除了{}条权限记录",delCount);
        if(CollectionUtils.isEmpty(functions)){
            return;
        }
        for(Function function : functions){
            DepartmentFunction df = new DepartmentFunction();
            df.setDepartmentId(id);
            df.setFunctionId(function.getId());
            departmentFunctionMapper.insertSelective(df);
        }
    }

    /**
     * 验证id合法性
     * @param id
     * @return
     */
    private Department getDepartment(Integer id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        if(department == null || department.getDeleted()) throw new BusinessException(404,"该部门不存在或已删除");
        return department;
    }
}
