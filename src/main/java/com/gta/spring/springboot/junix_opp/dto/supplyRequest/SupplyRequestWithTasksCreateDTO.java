package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import lombok.Value;

import java.util.List;

@Value
public class SupplyRequestWithTasksCreateDTO {
    Long number;
    String groupOfSupply;
    String descriprion;
    String comment;
    Long drawingId;
    Long revisionId;
    List<TaskEditCreateDTO> taskDTOList;
}
