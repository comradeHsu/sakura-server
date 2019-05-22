package com.sakura.study.controller.adminController;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.model.Employee;
import com.sakura.study.model.Professor;
import com.sakura.study.service.ProfessorService;
import com.sakura.study.utils.Assert;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Resource(name = "employeeCache")
    private LoadingCache<String, Optional<Employee>> employeeCache;

    /**
     * 添加教授
     * @param token
     * @param professor
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@RequestHeader("Token") String token, @RequestBody Professor professor){
        Employee employee = employeeCache.getUnchecked(token).orElse(null);
        Assert.notNull(employee,"用户信息不存在");
        professorService.add(professor,employee);
        return ResponseResult.success(professor);
    }

    /**
     * 删除教授
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@RequestHeader("Token") String token, @PathVariable("id") Integer id){
        Employee employee = employeeCache.getUnchecked(token).orElse(null);
        Assert.notNull(employee,"用户信息不存在");
        professorService.delete(id,employee);
        return ResponseResult.success("删除成功",null);
    }

    /**
     * 修改教授
     * @param token
     * @param professor
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseResult edit(@RequestHeader("Token") String token, @RequestBody Professor professor, @PathVariable("id") Integer id){
        Employee employee = employeeCache.getUnchecked(token).orElse(null);
        Assert.notNull(employee,"用户信息不存在");
        professor.setId(id);
        professorService.edit(professor,employee);
        return ResponseResult.success("修改成功",null);
    }

    /**
     * 教授信息的分页列表
     * @param page
     * @return
     */
    @RequestMapping(value = "/professors",method = RequestMethod.GET)
    public ResponseResult get(PageRequest page){
        page.initSkip();
        return professorService.getPage(page);
    }
}
