package com.sakura.study.controller.adminController;

import com.sakura.study.utils.MailReceives;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailContriller {
    @RequestMapping(value = "/getEmail")
    public Object getEamil() {
        return MailReceives.getEmail("test6@sakura.com","admin123_");
    }
}
