package com.gta.spring.springboot.junix_opp.dto.zone;

import lombok.Value;

@Value
public class ZoneReadDTO {
    Integer id;
    String name;
    String fullName;
    String code;
    int ordernumber;
    String declaration;
    String comment;
    Integer projectId;
    String projectName;
    Boolean isArchive;
    Boolean isOnDelete;

}
