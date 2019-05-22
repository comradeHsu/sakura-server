package com.sakura.study.dao;

import com.sakura.study.dto.CommunicationRecordDto;
import com.sakura.study.dto.CommunicationRequest;
import com.sakura.study.model.CommunicationRecord;
import com.sakura.study.utils.ResponseResult;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;


public interface CommunicationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunicationRecord record);

    int insertSelective(CommunicationRecord record);

    CommunicationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunicationRecord record);

    int updateByPrimaryKey(CommunicationRecord record);

    List<CommunicationRecordDto> getPages(CommunicationRequest request);

    @Select("select count(1) from communication_record")
    Integer count(CommunicationRequest request);
}