package com.sakura.study.service;

import com.sakura.study.dto.ChangePassword;
import com.sakura.study.dto.EmployeePageRequest;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Employee;
import com.sakura.study.model.Function;
import com.sakura.study.utils.ResponseResult;

import java.util.List;

public interface EmployeeService {
    /**
     * 员工登录
     * @param employee
     * @return
     */
    Employee login(Employee employee);

    /**
     * 根据管理员获取权限
     * @param employeeId
     * @return
     */
    List<Function> getFunctionByUser(Integer employeeId);

    /**
     * 根据所有权限
     * @return
     */
    List<Function> getAllFunction();

    /**
     * 添加工作人员
     * @param employee
     */
    void add(Employee employee,String token);

    /**
     * 修改管理人员
     * @param employee
     * @param id
     * @param token
     */
    void edit(Employee employee,Integer id, String token);

    /**
     * 删除管理人员
     * @param id
     * @param token
     * @return
     */
    void deleted(Integer id, String token);

    /**
     * 修改自己的密码
     * @param data
     */
    void editPwd(ChangePassword data,String token);

    /**
     * 管理员列表
     * @param id
     * @param page
     * @return
     */
    ResponseResult getPageEmployee(Integer id, EmployeePageRequest page);
}
