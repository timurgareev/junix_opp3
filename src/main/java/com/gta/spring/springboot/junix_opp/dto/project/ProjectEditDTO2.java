package com.gta.spring.springboot.junix_opp.dto.project;

import lombok.Value;

@Value
public class ProjectEditDTO2 {

    String name;
    String fullname;
    String code;
    Integer objectId;
    Boolean isArchive;
    Boolean isOnDelete;

}