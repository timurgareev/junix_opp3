package com.gta.spring.springboot.junix_opp.dto.drawing;

import lombok.Value;

@Value
public class DrawingEditDTO2 {

    String code;
    String name;

    Integer zoneId;

    Integer state;
    String description;
    String codeString;
    String markDrawingKey;
    Integer drawingMarksId;

    Boolean isArchive;
    Boolean isOnDelete;
}
