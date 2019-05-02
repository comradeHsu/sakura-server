package com.sakura.study.dao;

import com.sakura.study.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     *
     * @param start
     * @param pageCount
     * @return
     */
    List<User> getPageUser(@Param("start")Integer start, @Param("pageCount")Integer pageCount);

    /**
     * 获取用户总数
     * @return
     */
    int getPageUserCount();
}