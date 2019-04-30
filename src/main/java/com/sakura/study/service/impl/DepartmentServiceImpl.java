package com.sakura.study.service.impl;

import com.sakura.study.dao.DepartmentMapper;
import com.sakura.study.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;


}
