package com.sakura.study.service.impl;

import com.sakura.study.dao.FunctionMapper;
import com.sakura.study.model.Function;
import com.sakura.study.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService{

    @Autowired
    FunctionMapper functionMapper;


    /**
     * 获取所有的权限
     *
     * @return
     */
    @Override
    public List<Function> getAllFunctions() {
        return functionMapper.getAllFunction();
    }
}
