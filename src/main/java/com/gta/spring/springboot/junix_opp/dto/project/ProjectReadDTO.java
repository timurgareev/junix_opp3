package com.gta.spring.springboot.junix_opp.dto.project;

import lombok.Value;

@Value
public class ProjectReadDTO {
    Integer id;
    String name;
    String fullname;
    String code;
    int ordernumber;
    String declaration;
    Integer objectId;
    String objectName;
    Boolean isArchive;
    Boolean isOnDelete;


}