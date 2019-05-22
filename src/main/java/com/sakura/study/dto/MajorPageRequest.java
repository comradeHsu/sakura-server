package com.sakura.study.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MajorPageRequest extends PageRequest{

    private String majorName;

    private String degreeType;

    private Integer universityId;
}
