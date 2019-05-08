package com.sakura.study.controller.adminController;

import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.User;
import com.sakura.study.service.UserService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class ConsumerController {

    @Autowired
    UserService userService;

    /**
     * 获取用户分页列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public ResponseResult getPageUsers(PageRequest page,String realName){
        if(StringUtils.isEmpty(realName)){
            realName = null;
        }
        page.initSkip();
        return userService.getPageUsers(page,realName);
    }

    /**
     * 获取家长列表，可搜索
     * @param username
     * @return
     */
    @RequestMapping(value = "/parents",method = RequestMethod.GET)
    public ResponseResult getParents(String username){
        if(username != null && username.trim().isEmpty())
            username = null;
        List<User> parents = userService.getParents(username);
        return ResponseResult.success(parents);
    }

    /**
     * 添加用户
     * @param token
     * @param user
     * @return
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResponseResult add(@RequestHeader("Token") String token, @RequestBody User user){
        if(user.getParentId() == null)
            user.setParentId(0);
        User record = userService.add(token,user);
        return ResponseResult.success("添加成功",record);
    }

    /**
     * 修改用户
     * @param token
     * @param user
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseResult edit(@RequestHeader("Token") String token, @RequestBody User user,
                               @PathVariable("id") Integer id){
        if(user.getParentId() == null)
            user.setParentId(0);
        user.setId(id);
        userService.edit(token,user);
        return ResponseResult.success("修改成功",null);
    }

    /**
     * 删除用户
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable("id") Integer id, @RequestHeader("Token") String token){
        userService.delete(token,id);
        return ResponseResult.success("删除成功",null);
    }
}
