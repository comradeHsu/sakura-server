package com.sakura.study.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniversityPageRequest extends PageRequest{

    private String name;

    private String province;

    private String city;

    private Integer rankingTop;

    private Integer rankingBottom;

    private Integer parentId;

    private Integer subId;
}
