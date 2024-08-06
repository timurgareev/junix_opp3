package com.gta.spring.springboot.junix_opp.dto.drawing.projectTree;

import lombok.Value;

@Value
public class FlatDrawingDTO {
     Long drawingId;
     String drawingCode;
     Boolean drawingIsArchive;

    Integer zoneId;
     String zoneName;
     String zoneCode;
     Boolean zoneIsArchive;

    Integer projectId;
     String projectName;
     Boolean projectIsArchive;

    Integer objectId;
     String objectName;
     Boolean objectIsArchive;

    Integer groupId;
    String groupName;
    Boolean groupIsArchive;
}
