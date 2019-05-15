package com.sakura.study.controller.adminController;

import com.sakura.study.dao.ProfessorMapper;
import com.sakura.study.model.Professor;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professor")
public class ProfessorController {


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseResult add(@RequestHeader("Token") String token, @RequestBody Professor professor){
        return null;
    }
}
