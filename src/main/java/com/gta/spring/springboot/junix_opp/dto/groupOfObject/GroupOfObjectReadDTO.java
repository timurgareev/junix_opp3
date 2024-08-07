package com.gta.spring.springboot.junix_opp.dto.groupOfObject;

import lombok.Value;

@Value
public class GroupOfObjectReadDTO {
    Integer id;
    String name;
    String fullname;
    String comment;
    int ordernumber;
    Boolean isArchive;
    Boolean isOnDelete;
}
