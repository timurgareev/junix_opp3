package com.gta.spring.springboot.junix_opp.dto.drawing;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadMapper;
import com.gta.spring.springboot.junix_opp.dto.scope.ScopeReadDTO;
import com.gta.spring.springboot.junix_opp.dto.scope.ScopeReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DrawingMaxInfoReadMapper implements Mapper<Drawing, DrawingMaxInfoReadDTO> {

    @Override
    public DrawingMaxInfoReadDTO map(Drawing object) {
        return new DrawingMaxInfoReadDTO(
                object.getId(),
                object.getCode(),
                object.getName(),
                object.getZone().getProject().getId(),
                object.getZone().getProject().getName(),
                object.getZone().getId(),
                object.getZone().getName(),
                object.getState(),
                object.getDescription(),
                object.getCodestring(),
                object.getMarkDrawingKey(),
                object.getDrawingsMark() !=null ? object.getDrawingsMark().getId():null,
                object.getDrawingsMark() !=null ? object.getDrawingsMark().getMarkkey():null,

                object.getDrawingsMark() !=null && object.getDrawingsMark().getMark() !=null ? object.getDrawingsMark().getMark().getId():null,
                object.getDrawingsMark() !=null && object.getDrawingsMark().getMark() !=null ? object.getDrawingsMark().getMark().getName():null,
                object.getDrawingsMark() !=null && object.getDrawingsMark().getMark() !=null ? object.getDrawingsMark().getMark().getMarkkey():null,

                object.getDrawingsMark() !=null
                        && object.getDrawingsMark().getMark() !=null
                        && object.getDrawingsMark().getMark().getDiscipline() !=null
                        ? object.getDrawingsMark().getMark().getDiscipline().getId():null,
                object.getDrawingsMark() !=null
                        && object.getDrawingsMark().getMark() !=null
                        && object.getDrawingsMark().getMark().getDiscipline() !=null
                        ? object.getDrawingsMark().getMark().getDiscipline().getName():null,

                object.getDrawingsMark() !=null
                        && object.getDrawingsMark().getMark() !=null
                        && object.getDrawingsMark().getMark().getDiscipline() !=null
                        && object.getDrawingsMark().getMark().getDiscipline().getGroupDiscipline()!=null
                        ? object.getDrawingsMark().getMark().getDiscipline().getGroupDiscipline().getId():null,
                object.getDrawingsMark() !=null
                        && object.getDrawingsMark().getMark() !=null
                        && object.getDrawingsMark().getMark().getDiscipline() !=null
                        && object.getDrawingsMark().getMark().getDiscipline().getGroupDiscipline()!=null
                        ? object.getDrawingsMark().getMark().getDiscipline().getGroupDiscipline().getName():null,


                object.getIsArchive(),
                object.getIsOnDelete()
        );
    }

}
