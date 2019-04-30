package com.sakura.study.dao;

import com.sakura.study.model.Employee;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    /**
     * 根据用户名查找员工
     * @param username
     * @return
     */
    Employee findByUsername(String username);
}