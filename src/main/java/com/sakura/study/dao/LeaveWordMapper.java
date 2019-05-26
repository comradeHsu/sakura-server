package com.sakura.study.dao;

import com.sakura.study.dto.WordPageRequest;
import com.sakura.study.model.LeaveWord;

import java.util.List;

public interface LeaveWordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaveWord record);

    int insertSelective(LeaveWord record);

    LeaveWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaveWord record);

    int updateByPrimaryKey(LeaveWord record);

    List<LeaveWord> getPage(WordPageRequest page);

    int getPageCount(WordPageRequest page);
}