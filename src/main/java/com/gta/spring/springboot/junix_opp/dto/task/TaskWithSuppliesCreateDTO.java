package com.gta.spring.springboot.junix_opp.dto.task;

import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestCreateDTO;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class TaskWithSuppliesCreateDTO {
    String name;
    Integer taskTypeId;
    Long drawingId;
    Long revisionId;
    Integer projectId;
    String description;
    Long responsibleUserId;
    LocalDate deadlineDate;
    String priorityName;
    Integer taskStatusPrivate;
    Integer taskStatusPublic;
    List<SupplyRequestCreateDTO> supplyList;

}
