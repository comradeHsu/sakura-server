package com.sakura.study.dao;

import com.sakura.study.model.Assessment;

public interface AssessmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Assessment record);

    int insertSelective(Assessment record);

    Assessment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Assessment record);

    int updateByPrimaryKey(Assessment record);

    /**
     * 根据userId删除记录
     * @param userId
     * @return
     */
    int deleteByUserId(Integer userId);
}