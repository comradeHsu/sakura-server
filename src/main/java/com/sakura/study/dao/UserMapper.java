package com.sakura.study.dao;

import com.sakura.study.dto.UserDto;
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

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 获取用户详细信息
     * @param id
     * @return
     */
    UserDto getUserInfo(Integer id);

    /**
     * 根据用户名查找用户
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * 获取孩子列表
     * @param userId
     * @return
     */
    List<User> getChildren(Integer userId);
}