package com.sakura.study.dto;

import com.sakura.study.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends User{

    private String parentUsername;

    private String parentPhone;

}
