package com.sakura.study.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Message {

    private Integer userId;

    private Date time;

    private Integer type;

    private String content;
}
