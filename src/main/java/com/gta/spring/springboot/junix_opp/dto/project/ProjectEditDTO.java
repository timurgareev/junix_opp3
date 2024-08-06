package com.gta.spring.springboot.junix_opp.dto.project;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ProjectEditDTO {
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    String name;
    String fullname;
    String code;
    @NotNull(message = "ordernumber cannot be null")
    int ordernumber;
    String declaration;
    @NotNull(message = "objectId cannot be null")
    Integer objectId;
}