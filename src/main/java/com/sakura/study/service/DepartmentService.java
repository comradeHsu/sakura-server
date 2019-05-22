package com.sakura.study.service;

import com.sakura.study.dto.DepartmentDto;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Department;
import com.sakura.study.utils.ResponseResult;

import java.util.List;

public interface DepartmentService {
    /**
     * 添加部门
     * @param department
     */
    void add(DepartmentDto department);

    /**
     * 删除部门
     * @param id
     */
    void delete(Integer id);

    /**
     *获取分页的部门信息
     * @param page
     * @return
     */
    ResponseResult getPageDepartment(PageRequest page);

    /**
     * 获取所有的部门信息
     * @return
     */
    List<Department> getAllDepartment();

    /**
     * 修改部门信息和权限
     * @param data
     * @param id
     */
    void edit(DepartmentDto data,Integer id);
}
