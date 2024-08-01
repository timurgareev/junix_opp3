package com.gta.spring.springboot.junix_opp.dto.task;

import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestReadDTO;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Value
public class TaskWithSuppliesReadDTO {
    Long id;
    String name;
    Integer taskTypeId;
    String taskTypeName;
    Long drawingId;
    String drawingCode;
    Long revisionId;
    String revisionName;
    Integer projectId;
    String projectName;
    String description;
    Boolean isQuestion;
    LocalDateTime createdDate;
    Long createdUserId;
    String createdUsername;
    Long responsibleUserId;
    String responsibleUsername;
    LocalDate deadlineDate;
    Boolean isComplete;
    LocalDateTime completeDate;
    String priority;
    Integer taskStatusPrivateId;
    String taskStatusPrivateName;
    Integer taskStatusPublicId;
    String taskStatusPublicName;
    String report;
    String link1;
    String Link2;
    List<SupplyRequestReadDTO> supplyRequests;
}
