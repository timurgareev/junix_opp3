package com.gta.spring.springboot.junix_opp.dto.dto;

import com.gta.spring.springboot.junix_opp.entity.Discipline;
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


}
