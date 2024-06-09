package com.gta.spring.springboot.junix_opp.dto.task;

import com.gta.spring.springboot.junix_opp.entity.enums.ERole;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
public class TaskEditCreateDTO {
    String name;
    Integer taskTypeId;
    Long drawingId;
    Long revisionId;
    Integer projectId;
    String description;
//    LocalDateTime createdDate;
//    Long createdUserId;
    Long responsibleUserId;
    LocalDate deadlineDate;
    String priorityName;
    Integer taskStatusPrivate;
    Integer taskStatusPublic;
}
