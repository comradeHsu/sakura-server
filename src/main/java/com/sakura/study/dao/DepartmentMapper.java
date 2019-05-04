package com.sakura.study.dao;

import com.sakura.study.dto.DepartmentDto;
import com.sakura.study.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    /**
     * 根据部门名字查找部门
     * @param department
     * @return
     */
    Department findByName(String department);

    /**
     * 获取部门的员工数量
     * @param id
     * @return
     */
    int getDepartmentEmployeeCount(Integer id);

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 获取分页的部门
     * @param start
     * @param pageCount
     * @return
     */
    List<DepartmentDto> getPageDepartment(@Param("start")Integer start, @Param("pageCount")Integer pageCount);

    /**
     * 获取所有部门数量
     * @return
     */
    int getDepartmentCount();

    /**
     * 获取所有的部门信息
     * @return
     */
    List<Department> getAllDepartment();
}