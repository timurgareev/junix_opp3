package com.gta.spring.springboot.junix_opp.dto.drawing;

import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.dto.scope.ScopeReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class DrawingMaxInfoReadDTO {
    Long id;
    String code;
    String name;
    Integer projectId;
    String projectName;
    Integer zoneId;
    String zoneName;
    Integer state;
    String description;
    String codestring;
    String markDrawingKey;
    Integer drawingMarkId;
    String drawingMarkKey;
    Integer marksId;
    String marksName;
    String markKey;
    Integer disciplinesId;
    String disciplinesName;
    Integer groupDisciplinesId;
    String groupDisciplinesName;
    Boolean isArchive;
    Boolean isOnDelete;
}
