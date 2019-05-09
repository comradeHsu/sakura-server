package com.sakura.study.service.impl;

import com.sakura.study.dao.UserMapper;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.UserDto;
import com.sakura.study.model.User;
import com.sakura.study.service.UserService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.MD5Util;
import com.sakura.study.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 获取分页的用户列表
     *
     * @param page
     * @return
     */
    @Override
    public ResponseResult getPageUsers(PageRequest page,String realName) {
        List<User> users = userMapper.getPageUser(page.getSkip(),page.getPageCount(),realName);
        int dataCount = userMapper.getPageUserCount(realName);
        return ResponseResult.pageResult(users,dataCount);
    }

    /**
     * 获取家列表，可搜索
     *
     * @param username
     * @return
     */
    @Override
    public List<User> getParents(String username) {
        return userMapper.getParents(username);
    }

    /**
     * 添加用户
     *
     * @param token
     * @param user
     * @return
     */
    @Override
    public User add(String token, User user) {
        getParentByParentId(user);
        user.setPassword(MD5Util.md5Encode(user.getPassword()));
        userMapper.insertSelective(user);
        return user;
    }

    /**
     * 修改用户
     *
     * @param token
     * @param user
     */
    @Override
    public void edit(String token, User user) {
        getUserById(user.getId());
        getParentByParentId(user);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除用户
     * @param token
     * @param id
     */
    @Override
    @Transactional
    public void delete(String token, Integer id) {
        User user = getUserById(id);
        user.setDeleted(true);
        userMapper.updateByPrimaryKeySelective(user);
        if(user.getParentId() != 0) {
            int count = userMapper.resetParentId(user.getParentId());
            logger.info("重置了{}为用户的家长信息",count);
        }
    }

    /**
     * api
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User record = userMapper.findByUsername(user.getUsername());
        if(record == null)
            throw new BusinessException(404,"用户不存在");
        if(!MD5Util.md5Encode(user.getPassword()).equals(record.getPassword()))
            throw new BusinessException(400,"用户名或密码错误");
        return record;
    }

    /**
     * api
     * 获取用户详细信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserDto getUserInfo(Integer userId) {
        UserDto user = userMapper.getUserInfo(userId);
        if(user == null) throw new BusinessException(404,"此用户不存在或已删除");
        return user;
    }

    /**
     * 用户注册
     * api
     * @param userDto
     * @return
     */
    @Override
    public UserDto register(UserDto userDto) {
        if(!StringUtils.isEmpty(userDto.getParentPhone())){
            User parent = userMapper.findByPhone(userDto.getParentPhone());
            if(parent == null) throw new BusinessException(400,"家长账号不存在");
            userDto.setParentId(parent.getId());
        }
        if(!StringUtils.isEmpty(userDto.getUsername())){
            User record = userMapper.findByUsername(userDto.getUsername());
            if(record != null) throw new BusinessException(400,"此账号已存在");
        } else {
            userDto.setUsername(userDto.getPhoneNumber());
        }
        User user = userMapper.findByPhone(userDto.getPhoneNumber());
        if(user != null) throw new BusinessException(400,"此手机号已被使用");
        if(StringUtils.isEmpty(userDto.getRealName())) userDto.setRealName("用户"+Math.random() * 100000);
        userDto.setPassword(MD5Util.md5Encode(userDto.getPassword()));
        userMapper.insertSelective(userDto);
        return userDto;
    }

    /**
     * 没有则抛出异常
     * @param id
     */
    private User getUserById(Integer id) {
        User record = userMapper.selectByPrimaryKey(id);
        if(record == null || record.getDeleted()) throw new BusinessException(404,"此用户不存在或已删除");
        return record;
    }

    /**
     * 检查家长账号有效性
     * @param user
     */
    private void getParentByParentId(User user) {
        if(user.getParentId() != 0){
            try {
                getUserById(user.getParentId());
            } catch (BusinessException e){
                throw new BusinessException(404,"家长账号不存在或已删除");
            }
        }
    }
}
