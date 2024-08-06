package com.gta.spring.springboot.junix_opp.dto.zone;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ZoneEditDTO {
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    String name;
    String fullName;
    @NotEmpty(message = "code cannot be empty")
    String code;
    @NotNull(message = "ordernumber cannot be null")
    int ordernumber;
    String declaration;
    String comment;
    @NotNull(message = "projectId cannot be null")
    Integer projectId;

}
