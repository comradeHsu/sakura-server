package com.sakura.study.adminController;

import com.sakura.study.model.Employee;
import com.sakura.study.service.EmployeeService;
import com.sakura.study.utils.Assert;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    /**
     * 管理员登录
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseResult login(HttpSession session, @RequestBody Employee employee){
        String message = "用户名或密码不能为空";
        Assert.notEmpty(employee.getUsername(),message);
        Assert.notEmpty(employee.getPassword(),message);
        Employee record = employeeService.login(employee);
        session.setAttribute("employee",record);
        return ResponseResult.success(record);
    }


}
