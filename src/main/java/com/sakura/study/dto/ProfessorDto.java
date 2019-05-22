package com.sakura.study.dto;

import com.sakura.study.model.Professor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfessorDto extends Professor {

    private Integer universityId;

    private String schoolName;

    private String majorName;
}
