package com.gta.spring.springboot.junix_opp.dto.event;

import lombok.Value;

import java.time.LocalDate;

@Value
public class EventEditCreateDTO {
//    Long id;
    String name;
    String description;
    Integer eventTypeID;
    Long drawingId;
    Long revisionId;
    LocalDate eventDate;
    Boolean isSystemCreated;
}
