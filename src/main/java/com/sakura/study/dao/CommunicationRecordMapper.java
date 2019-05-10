package com.sakura.study.dao;

import com.sakura.study.model.CommunicationRecord;


public interface CommunicationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunicationRecord record);

    int insertSelective(CommunicationRecord record);

    CommunicationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunicationRecord record);

    int updateByPrimaryKey(CommunicationRecord record);
}