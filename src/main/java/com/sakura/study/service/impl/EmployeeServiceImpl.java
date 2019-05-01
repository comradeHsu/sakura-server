package com.sakura.study.service.impl;

import com.sakura.study.dao.EmployeeMapper;
import com.sakura.study.model.Employee;
import com.sakura.study.service.EmployeeService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;


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
            throw new BusinessException(404,"用户名或密码错误");
        return record;
    }
}
