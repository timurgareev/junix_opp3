package com.gta.spring.springboot.junix_opp.dto.object;

import lombok.Value;

@Value
public class Object2ReadDTO {
    Integer id;
    String name;
    String fullname;
    String comment;
    int ordernumber;
    Integer groupOfObject_id;
    String groupOfObject_name;
    Boolean isArchive;
    Boolean isOnDelete;
    }

