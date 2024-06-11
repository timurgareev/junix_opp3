package com.gta.spring.springboot.junix_opp.dto.event;

import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EventWithNewTaskCreateDTO {
    String name;
    String description;
    Integer eventTypeID;
    Long drawingId;
    Long revisionId;
    LocalDate eventDate;
    Boolean isSystemCreated;
    List<TaskEditCreateDTO> tasks;
}
