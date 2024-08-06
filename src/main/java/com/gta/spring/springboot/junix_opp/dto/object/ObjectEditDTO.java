package com.gta.spring.springboot.junix_opp.dto.object;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ObjectEditDTO {
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    String name;
    String fullname;
    String comment;
    @NotNull(message = "ordernumber cannot be null")
    int ordernumber;
    @NotNull(message = "groupOfObjectId cannot be null")
    Integer groupOfObjectId;
//    @NotNull(message = "isArchive cannot be null")
//    Boolean isArchive;
//    @NotNull(message = "isOnDelete cannot be null")
//    Boolean isOnDelete;
    }

