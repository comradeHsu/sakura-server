package com.sakura.study.dao;

import com.sakura.study.model.Professor;

public interface ProfessorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Professor record);

    int insertSelective(Professor record);

    Professor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Professor record);

    int updateByPrimaryKey(Professor record);
}