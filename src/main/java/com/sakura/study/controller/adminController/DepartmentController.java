package com.sakura.study.controller.adminController;

import com.sakura.study.dto.DepartmentDto;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Department;
import com.sakura.study.service.DepartmentService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        departmentService.add(department);
        return ResponseResult.success("添加成功",null);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable("id") Integer id){
        departmentService.delete(id);
        return ResponseResult.success("删除成功",null);
    }

    /**
     * 获取部门列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/departments",method = RequestMethod.GET)
    public ResponseResult departments(PageRequest page){
        return departmentService.getPageDepartment(page);
    }

    /**
     * 获取全部部门，用户选择用
     * @return
     */
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseResult getAllDepartments(){
        List<Department> data = departmentService.getAllDepartment();
        return ResponseResult.success(data);
    }

    /**
     * 修改部门信息和权限
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseResult edit(@PathVariable("id") Integer id, @RequestBody DepartmentDto data){
        departmentService.edit(data,id);
        return ResponseResult.success("修改成功",null);
    }
}
