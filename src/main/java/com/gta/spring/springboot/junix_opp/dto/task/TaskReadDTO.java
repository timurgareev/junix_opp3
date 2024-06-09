package com.gta.spring.springboot.junix_opp.dto.task;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
public class TaskReadDTO {
    Long id;
    String name;
    String taskType;
    String drawingCode;
    String revisionName;
    Integer projectId;
    String description;
    LocalDateTime createdDate;
    String createdUsername;
    String responsibleUsername;
    LocalDate deadlineDate;
    String priority;
    String taskStatusPrivate;
    String taskStatusPublic;

}
