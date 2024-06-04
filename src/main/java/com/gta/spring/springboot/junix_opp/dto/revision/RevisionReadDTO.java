package com.gta.spring.springboot.junix_opp.dto.revision;

import lombok.Value;

import java.util.Date;

@Value
public class RevisionReadDTO {
    Long id;
    String name;
    String status;
    Date dataInbox;
    Date dateOutbox;
    Date inproducrionDateSystem;
    Date inproducrionDateManual;
    Integer rateNumber;
    Boolean isLatest;
    String comment1;
    String comment2;
//    String idDrawingRev;

}
