package com.sakura.study.dao;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.UserAgreementDto;
import com.sakura.study.model.UserAgreement;

import java.util.List;

public interface UserAgreementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAgreement record);

    int insertSelective(UserAgreement record);

    UserAgreement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAgreement record);

    int updateByPrimaryKey(UserAgreement record);

    /**
     * 查询是否有过往记录
     * @param userId
     * @return
     */
    UserAgreement selectByUserId(Integer userId);

    /**
     * 协议列表
     * @param page
     * @return
     */
    List<UserAgreementDto> getAgreements(PageRequest page);

    /**
     * 协议总数
     * @return
     */
    int getAgreementCount();
}