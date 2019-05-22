package com.sakura.study.dto;

import lombok.Data;

@Data
public class MailDto {
    String subject;
    String From;
    String to;
    String receiveAddress;
    String content;
    String date;
}
