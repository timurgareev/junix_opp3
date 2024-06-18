package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class SupplyRequestWithTasksReadDTO {
    Long id;
    Long number;
    String groupOfSupply;
    String descriprion;
    String comment;
    String drawingCode;
    String revisionName;
    LocalDateTime createdDate;
    String username;
    List<TaskReadDTO> taskDTOList;

}
