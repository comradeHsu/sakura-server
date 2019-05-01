package com.sakura.study.adminController;

import com.sakura.study.model.Department;
import com.sakura.study.service.DepartmentService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /**
     * 新增部门
     * @param department
     * @return
     */
    @RequestMapping(value = "/department",method = RequestMethod.POST)
    public ResponseResult add(@RequestBody Department department){
        return null;
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable("id") Integer id){
        return null;
    }
}
