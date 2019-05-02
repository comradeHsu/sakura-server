package com.sakura.study.dao;

import com.sakura.study.model.DepartmentFunction;

public interface DepartmentFunctionMapper {
    int insert(DepartmentFunction record);

    int insertSelective(DepartmentFunction record);

    /**
     * 根据部门id删除记录
     * @param departmentId
     * @return
     */
    int deleteByDepartmentId(Integer departmentId);
}