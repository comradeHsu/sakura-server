package com.sakura.study.dao;

import com.sakura.study.model.Employee;
import org.springframework.data.repository.query.Param;

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
     * @param id
     * @param start
     * @param pageCount
     * @return
     */
    List<Employee> getPageEmployee(@Param("id") Integer id,@Param("start")Integer start,@Param("pageCount")Integer pageCount);

    /**
     * 获取工作人员数量
     * @param id
     * @return
     */
    int getPageEmployeeCount(Integer id);
}