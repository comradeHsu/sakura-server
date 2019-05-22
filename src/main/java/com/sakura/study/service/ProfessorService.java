package com.sakura.study.service;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Employee;
import com.sakura.study.model.Professor;
import com.sakura.study.utils.ResponseResult;

public interface ProfessorService {
    /**
     * 添加教授
     * @param professor
     * @param employee
     * @return
     */
    Professor add(Professor professor, Employee employee);

    /**
     * 删除教授
     * @param id
     * @param employee
     */
    void delete(Integer id, Employee employee);

    /**
     * 修改教授信息
     * @param professor
     * @param employee
     * @return
     */
    void edit(Professor professor, Employee employee);

    /**
     * 获取教授信息的分页列表
     * @param page
     * @return
     */
    ResponseResult getPage(PageRequest page);
}
