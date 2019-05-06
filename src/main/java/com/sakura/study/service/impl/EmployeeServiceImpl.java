package com.sakura.study.service.impl;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dao.EmployeeMapper;
import com.sakura.study.dao.FunctionMapper;
import com.sakura.study.dto.ChangePassword;
import com.sakura.study.dto.EmployeePageRequest;
import com.sakura.study.model.Employee;
import com.sakura.study.model.Function;
import com.sakura.study.service.EmployeeService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.MD5Util;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private FunctionMapper functionMapper;

    @Resource(name = "employeeCache")
    private LoadingCache<String, Optional<Employee>> employeeCache;

    /**
     * 员工登录
     *
     * @param employee
     * @return
     */
    @Override
    public Employee login(Employee employee) {
        Employee record = employeeMapper.findByUsername(employee.getUsername());
        if(record == null)
            throw new BusinessException(404,"用户不存在");
        if(!MD5Util.md5Encode(employee.getPassword()).equals(record.getPassword()))
            throw new BusinessException(400,"用户名或密码错误");
        return record;
    }

    @Override
    public List<Function> getFunctionByUser(Integer employeeId) {
        return functionMapper.getFunctionByUser(employeeId);
    }

    @Override
    public List<Function> getAllFunction() {
        return functionMapper.getAllFunction();
    }

    /**
     * 添加工作人员
     *
     * @param employee
     */
    @Override
    public void add(Employee employee,String token) {
        Employee record = employeeMapper.findByUsername(employee.getUsername());
        if(record != null) throw new BusinessException(400,"用户名已存在");
        employee.setPassword(MD5Util.md5Encode(employee.getPassword()));
        employeeMapper.insertSelective(employee);
    }

    /**
     * 修改管理人员
     *
     * @param employee
     * @param id
     * @param token
     */
    @Override
    public void edit(Employee employee, Integer id, String token) {
        getEmployeeById(id);
        Employee byUsername = employeeMapper.findByUsername(employee.getUsername());
        if(byUsername != null && !Objects.equals(byUsername.getId(), id)) throw new BusinessException(400,"用户名已存在");
        employee.setId(id);
        employee.setPassword(null);
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 删除管理人员
     *
     * @param id
     * @param token
     * @return
     */
    @Override
    public void deleted(Integer id, String token) {
        Employee employee = getEmployeeById(id);
        employeeMapper.deletedById(id);
    }

    /**
     * 修改自己的密码
     *
     * @param data
     */
    @Override
    public void editPwd(ChangePassword data,String token) {
        Employee self = employeeCache.getUnchecked(token).orElse(null);
        if(data.getNewPassword() == null || data.getNewPassword().trim().isEmpty())
            throw new BusinessException(400,"密码格式不合法");
        if(!MD5Util.md5Encode(data.getOldPassword()).equals(self.getPassword()))
            throw new BusinessException(400,"原密码不正确");
        self.setPassword(MD5Util.md5Encode(data.getNewPassword()));
        employeeMapper.updateByPrimaryKeySelective(self);
        employeeCache.put(token,Optional.of(self));
    }

    /**
     * 管理员列表
     *
     * @param id
     * @param page
     * @return
     */
    @Override
    public ResponseResult getPageEmployee(Integer id, EmployeePageRequest page) {
        page.setSelfId(id);
        List<Employee> employees = employeeMapper.getPageEmployee(page);
        int dataCount = employeeMapper.getPageEmployeeCount(page);
        return ResponseResult.pageResult(employees,dataCount);
    }

    /**
     * 没有则抛出异常
     * @param id
     */
    private Employee getEmployeeById(Integer id) {
        Employee record = employeeMapper.selectByPrimaryKey(id);
        if(record == null || record.getDeleted()) throw new BusinessException(404,"此工作人员不存在或已删除");
        return record;
    }
}
