package com.sakura.study.dto;

import com.sakura.study.model.Employee;
import com.sakura.study.model.Function;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeDto extends Employee{

    private List<Function> functions;

}
