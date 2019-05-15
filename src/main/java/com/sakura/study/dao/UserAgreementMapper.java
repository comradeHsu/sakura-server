package com.sakura.study.dao;

import com.sakura.study.model.UserAgreement;

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
}