package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import lombok.Value;

import java.time.LocalDate;
import java.util.Date;


@Value
public class SupplyRequestCreateDTO {
    Long number;
    LocalDate supplyDate;
    String groupOfSupply;
    String description;
    String comment;
    Integer projectId;
    Long drawingId;
    Long revisionId;
}
