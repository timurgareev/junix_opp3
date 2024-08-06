package com.gta.spring.springboot.junix_opp.dto.groupOfObject;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class GroupOfObjectEditDTO {
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    String name;
    String fullname;
    String comment;
    int ordernumber;
//    @NotNull(message = "isArchive cannot be null")
//    Boolean isArchive;
//    @NotNull(message = "isOnDelete cannot be null")
//    Boolean isOnDelete;

}
