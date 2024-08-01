package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
public class SupplyRequestReadDTO {
    Long id;
    Long number;
    String groupOfSupply;
    String descriprion;
    String comment;
    Integer projectId;
    String projectName;
    Long drawingId;
    String drawingCode;
    Long revisionId;
    String revisionName;
    LocalDateTime createdDate;
    Long userId;
    String username;
    LocalDate supplyRequestDate;
    Integer year;
    String comment1c;
    String condition;
    String drawing_1c;
    LocalDate regDate;
    String regNumber;
    String status;
    String statusSign;


}
