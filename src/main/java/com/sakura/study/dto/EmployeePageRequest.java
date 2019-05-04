package com.sakura.study.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePageRequest extends PageRequest{

    private Integer departmentId;

    private Integer selfId;

    private String realName;

}
