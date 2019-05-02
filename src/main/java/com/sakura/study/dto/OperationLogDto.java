package com.sakura.study.dto;

import com.sakura.study.model.OperationLog;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperationLogDto extends OperationLog{

    private String username;

}
