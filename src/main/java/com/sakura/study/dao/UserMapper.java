package com.sakura.study.dao;

import com.sakura.study.model.User;
import org.apache.ibatis.annotations.Param;

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
    List<User> getPageUser(@Param("start")Integer start, @Param("pageCount")Integer pageCount,
                           @Param("realName")String realName);

    /**
     * 获取用户总数
     * @return
     */
    int getPageUserCount(@Param("realName")String realName);

    /**
     * 获取家列表，可搜索
     * @param username
     * @return
     */
    List<User> getParents(@Param("username") String username);

    /**
     * 重置parentId
     * @param parentId
     * @return
     */
    int resetParentId(Integer parentId);
}