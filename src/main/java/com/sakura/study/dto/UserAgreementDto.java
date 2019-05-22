package com.sakura.study.dto;

import com.sakura.study.model.UserAgreement;
import lombok.Data;

@Data
public class UserAgreementDto extends UserAgreement{

    private String username;

    private String realName;

    private String process;

    private Integer userProcess;

    private String otherFile;
}
