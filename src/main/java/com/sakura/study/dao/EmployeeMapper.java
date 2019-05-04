package com.sakura.study.dao;

import com.sakura.study.dto.EmployeePageRequest;
import com.sakura.study.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    int deletedById(Integer id);

    /**
     * 获取工作人员列表
     * @param page
     * @return
     */
    List<Employee> getPageEmployee(EmployeePageRequest page);

    /**
     * 获取工作人员数量
     * @param id
     * @param departmentId
     * @return
     */
    int getPageEmployeeCount(@Param("id") Integer id,@Param("departmentId")Integer departmentId);
}