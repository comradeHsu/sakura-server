package com.sakura.study.dto;

import com.google.common.collect.Lists;
import com.sakura.study.model.Employee;
import com.sakura.study.model.Function;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
public class EmployeeDto extends Employee{

    private List<Function> functions;

    private List<String> roles = Lists.newArrayList("admin");

    private String department;

    public EmployeeDto(Employee employee){
        BeanUtils.copyProperties(employee,this);
    }

    public EmployeeDto(){}
}
