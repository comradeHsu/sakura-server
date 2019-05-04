package com.sakura.study.adminController;

import com.sakura.study.model.Function;
import com.sakura.study.service.FunctionService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/function")
public class FunctionController {

    @Autowired
    FunctionService functionService;

    /**
     * 获取所有的权限
     * @return
     */
    @RequestMapping(value = "/functions",method = RequestMethod.GET)
    public ResponseResult getAllFunctions(){
        List<Function> data = functionService.getAllFunctions();
        return ResponseResult.success(data);
    }
}
