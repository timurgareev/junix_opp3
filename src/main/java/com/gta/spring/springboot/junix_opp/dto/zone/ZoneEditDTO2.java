package com.gta.spring.springboot.junix_opp.dto.zone;

import lombok.Value;

@Value
public class ZoneEditDTO2 {
    String name;
    String fullName;
    String code;
    int ordernumber;
    String declaration;
    String comment;
    Integer projectId;
    Boolean isArchive;
    Boolean isOnDelete;

}
