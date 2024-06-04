package com.gta.spring.springboot.junix_opp.dto.drawing;

import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.dto.scope.ScopeReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class DrawingWithRevisionsReadDTO {
    Long id;
    String code;
    String name;
    Integer state;
    String description;
    String codestring;
    String markDrawingKey;
    DrawingMarkReadDTO mark;
//    DisciplineReadDTO discipline;
    List<RevisionReadDTO> revision;
    List<ScopeReadDTO> scope;


}
