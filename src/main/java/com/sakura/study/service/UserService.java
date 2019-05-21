package com.sakura.study.service;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.UserAgreementDto;
import com.sakura.study.dto.UserDto;
import com.sakura.study.model.Assessment;
import com.sakura.study.model.User;
import com.sakura.study.model.UserAgreement;
import com.sakura.study.utils.ResponseResult;

import java.util.List;

public interface UserService {
    /**
     * 获取分页的用户列表
     * @param page
     * @return
     */
    ResponseResult getPageUsers(PageRequest page,String realName);
    /**
     * 获取家列表，可搜索
     * @param username
     * @return
     */
    List<User> getParents(String username);

    /**
     * 添加用户
     * @param token
     * @param user
     * @return
     */
    User add(String token, User user);

    /**
     * 修改用户
     * @param token
     * @param user
     */
    void edit(String token, User user);

    /**
     * 删除用户
     * @param token
     * @param id
     */
    void delete(String token, Integer id);

    /**
     * 获取分页的协议
     * @param page
     * @return
     */
    ResponseResult getAgreements(PageRequest page);

    /**
     * 修改用户流程
     * @param token
     * @param user
     */
    void editProcess(String token, User user);

    /**
     * api
     * 用户登录
     * @param user
     * @return
     */
    UserDto login(User user);

    /**
     * api
     * 获取用户详细信息
     * @param userId
     * @return
     */
    UserDto getUserInfo(Integer userId);

    /**
     * api
     * 用户注册
     * @param userDto
     * @return
     */
    UserDto register(UserDto userDto);

    /**
     * api
     * 用户修改自己的信息
     * @param user
     * @return
     */
    void editInfo(User user);

    /**
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * api
     * 用户评估
     * @param assessment
     * @param userId
     */
    void assessment(Assessment assessment,Integer userId);

    /**
     * 用户上传协议
     * @param agreement
     * @param user
     */
    void uploadAgreement(UserAgreement agreement,User user);

    /**
     * 获取孩子列表
     * @param userId
     * @return
     */
    List<User> getChildren(Integer userId);

    /**
     * 获取用户上传的协议
     * @param userId
     * @return
     */
    UserAgreementDto getUserAgreement(Integer userId);

    /**
     * api
     * 用户评估
     * @param userId
     */
    Assessment getAssessment(Integer userId);

    /**
     * api
     * 用户申请院校
     * @param userId
     */
    void apply(Integer userId);
}
