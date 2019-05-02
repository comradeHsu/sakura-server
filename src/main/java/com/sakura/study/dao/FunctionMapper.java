package com.sakura.study.dao;

import com.sakura.study.model.Function;

import java.util.List;

public interface FunctionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);

    /**
     * 获取用户对应的权限
     * @param employeeId
     * @return
     */
    List<Function> getFunctionByUser(Integer employeeId);

    /**
     * 获取所有的权限
     * @return
     */
    List<Function> getAllFunction();

    /**
     * 根据部门获取权限
     * @param departmentId
     * @return
     */
    List<Function> getFunctionByDepartment(Integer departmentId);
}