package com.sakura.study.service;

import com.sakura.study.model.Employee;

public interface EmployeeService {
    /**
     * 员工登录
     * @param employee
     * @return
     */
    Employee login(Employee employee);
}
