package com.sakura.study.controller.adminController;

import com.sakura.study.utils.MailReceives;
import com.sakura.study.utils.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailController {

    @RequestMapping(value = "/getEmail")
    public Object getEamil(String email) {
        return ResponseResult.success(MailReceives.getEmail(email,"admin123_"));
    }
}
