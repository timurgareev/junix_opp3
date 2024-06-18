package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class SupplyRequestReadDTO {
    Long id;
    Long number;
    String groupOfSupply;
    String descriprion;
    String comment;
    String drawingCode;
    String revisionName;
    LocalDateTime createdDate;
    String username;


}
