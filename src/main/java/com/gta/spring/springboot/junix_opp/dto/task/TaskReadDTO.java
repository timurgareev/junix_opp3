package com.gta.spring.springboot.junix_opp.dto.task;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
public class TaskReadDTO {
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

}
