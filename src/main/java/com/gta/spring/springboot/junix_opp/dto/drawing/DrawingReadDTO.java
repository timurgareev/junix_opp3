package com.gta.spring.springboot.junix_opp.dto.drawing;

import lombok.Value;

@Value
public class DrawingReadDTO {
    Long id;
    String code;
    String name;
    Integer projectId;
    String projectName;
    Integer zoneId;
    String zoneName;
    Integer state;
    String description;
    String codeString;
    String markDrawingKey;
    Integer drawingMarksId;
    String drawingsMarksKey;
    Boolean isArchive;
    Boolean isOnDelete;
}
