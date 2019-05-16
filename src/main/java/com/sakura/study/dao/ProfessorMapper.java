package com.sakura.study.dao;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.ProfessorDto;
import com.sakura.study.model.Professor;

import java.util.List;

public interface ProfessorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Professor record);

    int insertSelective(Professor record);

    Professor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Professor record);

    int updateByPrimaryKey(Professor record);

    /**
     * 分页数据
     * @param page
     * @return
     */
    List<ProfessorDto> getPageProfessor(PageRequest page);

    /**
     * 教授数量
     * @return
     */
    int getPageProfessorCount();
}