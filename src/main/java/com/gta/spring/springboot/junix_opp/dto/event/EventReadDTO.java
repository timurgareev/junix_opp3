package com.gta.spring.springboot.junix_opp.dto.event;

import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Value
public class EventReadDTO {
    Long id;
    String name;
    String description;
    String eventTypeName;
    String drawingCode;
    String revision_name;
    LocalDate eventDate;
    Boolean isSystemCreated;
}




