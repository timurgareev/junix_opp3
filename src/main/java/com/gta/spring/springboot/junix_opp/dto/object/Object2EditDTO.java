package com.gta.spring.springboot.junix_opp.dto.object;

import lombok.Value;

@Value
public class Object2EditDTO {

    String name;
    String fullname;
    String comment;
    int ordernumber;
    Integer groupOfObjectId;
    Boolean isArchive;
    Boolean isOnDelete;
    }

