package com.sakura.study.controller.adminController;

import com.sakura.study.dto.FinancePageRequest;
import com.sakura.study.model.Finance;
import com.sakura.study.service.FinanceService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/finance")
public class FinanceController {

    @Autowired
    FinanceService financeService;

    /**
     * 获取分页的财务信息列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/finances",method = RequestMethod.GET)
    public ResponseResult getFinances(FinancePageRequest request){
        return financeService.getFinances(request);
    }

    /**
     * 添加财务记录
     * @param token
     * @param finance
     * @return
     */
    @RequestMapping(value = "/finance",method = RequestMethod.POST)
    public ResponseResult add(@RequestHeader("Token")String token, @RequestBody Finance finance){
        Finance record = financeService.add(token,finance);
        return ResponseResult.success("添加成功",record);
    }

    /**
     * 修改财务信息
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseResult edit(@PathVariable("id") Integer id, @RequestHeader("Token")String token,@RequestBody Finance finance){
        finance.setId(id);
        financeService.edit(token,finance);
        return ResponseResult.success("修改成功",null);
    }

    /**
     * 删除财务信息
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult deleted(@PathVariable("id") Integer id,@RequestHeader("Token")String token){
        financeService.deleted(token,id);
        return ResponseResult.success("删除成功",null);
    }
}
