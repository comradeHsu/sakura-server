package com.sakura.study.dto;

import com.sakura.study.model.Department;
import com.sakura.study.model.Function;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DepartmentDto extends Department{

    private int employeeCount;

    private List<Function> functions;

}
