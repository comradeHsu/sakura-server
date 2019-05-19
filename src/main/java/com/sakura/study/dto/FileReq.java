package com.sakura.study.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileReq extends PageRequest {
    public String userName;
}
