package com.sakura.study.service.impl;

import com.sakura.study.dao.UserMapper;
import com.sakura.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
}
