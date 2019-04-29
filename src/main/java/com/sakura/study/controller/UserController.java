package com.sakura.study.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class UserController {
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    @ResponseBody

    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path")
    public Object getUserById() {
        HashMap user =  new HashMap<String,String>();
        user.put("name","王二");
        return user;
    }
}
