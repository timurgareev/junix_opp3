package com.gta.spring.springboot.junix_opp.dto.drawing;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class DrawingEditDTO {
    @NotNull(message = "code cannot be null")
    @NotEmpty(message = "code cannot be empty")
    String code;
    String name;
    @NotNull(message = "zoneId cannot be null")
    Integer zoneId;
    Integer state;
    String description;
    String codeString;
    String markDrawingKey;
    Integer drawingMarksId;

}
