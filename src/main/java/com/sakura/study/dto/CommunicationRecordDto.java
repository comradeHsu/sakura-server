package com.sakura.study.dto;

import com.sakura.study.model.CommunicationRecord;
import lombok.Data;

@Data
public class CommunicationRecordDto extends CommunicationRecord {

    private String userUsername;

    private String employeeUsername;
}
