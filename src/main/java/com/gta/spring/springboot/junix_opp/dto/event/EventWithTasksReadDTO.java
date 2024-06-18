package com.gta.spring.springboot.junix_opp.dto.event;

import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Value
public class EventWithTasksReadDTO {
    Long id;
    String name;
    String description;
    String eventTypeName;
    String drawingCode;
    String revision_name;
    LocalDate eventDate;
    Boolean isSystemCreated;

    List<TaskReadDTO> tasks;
}




