package com.sakura.study.service.impl;

import com.sakura.study.dao.UserMapper;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.User;
import com.sakura.study.service.UserService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 获取分页的用户列表
     *
     * @param page
     * @return
     */
    @Override
    public ResponseResult getPageUsers(PageRequest page) {
        List<User> users = userMapper.getPageUser(page.getSkip(),page.getPageCount());
        int dataCount = userMapper.getPageUserCount();
        return ResponseResult.pageResult(users,dataCount);
    }
}
