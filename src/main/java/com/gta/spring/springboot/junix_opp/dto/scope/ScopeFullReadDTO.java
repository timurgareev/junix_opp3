package com.gta.spring.springboot.junix_opp.dto.scope;

import lombok.Value;

@Value
public class ScopeFullReadDTO {
    String groupOfObjectName;
    String objectName;
    String projectName;

    String zoneCode;
    String zoneName;

    Long drawingId;
    String drawingCode;
    String drawingDescription;
    String markDrawing;
    String markKey;
    String disciplineName;
    String disciplineGroupName;

    Long id;
    String name;

    Long revisionId;
    String revisionName;

    Integer typeOfWorkId;
    String typeOfWork;

    Integer unitId;
    String unitName;

    Double quantity;
    String comment;

}
