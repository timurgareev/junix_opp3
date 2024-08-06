package com.gta.spring.springboot.junix_opp.dto.revision;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.Date;

@Value
public class RevisionEditDTO {
    @NotNull(message = "drawingId cannot be null")
    Long drawingId;
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    String name;
    String status;
    Date dataInbox;
    Date dateOutbox;
    Date inproducrionDateSystem;
    Date inproductionDateManual;
    Integer rateNumber;
    Boolean isLatest;
    String comment1;
    String comment2;
    Boolean isProcced;


}
