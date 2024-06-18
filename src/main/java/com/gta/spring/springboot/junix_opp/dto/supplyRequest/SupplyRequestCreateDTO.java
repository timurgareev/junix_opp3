package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import lombok.Value;


@Value
public class SupplyRequestCreateDTO {
    Long number;
    String groupOfSupply;
    String descriprion;
    String comment;
    Long drawingId;
    Long revisionId;
}
