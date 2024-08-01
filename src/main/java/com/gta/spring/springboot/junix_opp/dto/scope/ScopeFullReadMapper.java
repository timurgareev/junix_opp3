package com.gta.spring.springboot.junix_opp.dto.scope;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Scope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScopeFullReadMapper implements Mapper<Scope, ScopeFullReadDTO> {

    @Override
    public ScopeFullReadDTO map(Scope object) {


        return new ScopeFullReadDTO(
                object.getDrawing().getZone().getProject().getObject().getGroup_of_objects().getName(),
                object.getDrawing().getZone().getProject().getObject().getName(),
                object.getDrawing().getZone().getProject().getName(),
                object.getDrawing().getZone().getCode(),
                object.getDrawing().getZone().getName(),
                object.getDrawing().getId(),
                object.getDrawing().getCode(),
                object.getDrawing().getDescription(),
                object.getDrawing().getDrawingsMark().getMarkdrawing(),
                object.getDrawing().getDrawingsMark().getMark().getMarkkey(),
//                object.getDrawing().getDrawingsMark().getMark().getDiscipline().getMarks(),
                object.getDrawing().getDrawingsMark().getMark().getDiscipline().getName(),
                object.getDrawing().getDrawingsMark().getMark().getDiscipline().getGroupDiscipline().getName(),


                object.getId(),
                object.getName(),

                object.getRevision() !=null ? object.getRevision().getId() : null,
                object.getRevision() !=null ? object.getRevision().getName() : null,

                object.getTypeOfWork() !=null ? object.getTypeOfWork().getId() : null,
                object.getTypeOfWork() !=null ? object.getTypeOfWork().getName() : null,

                object.getUnit() !=null ? object.getUnit().getId() : null,
                object.getUnit() !=null ? object.getUnit().getCode() : null,

                ((object.getQuantity() !=null) && (!Double.isNaN(object.getQuantity()))) ? object.getQuantity() : 0,
                object.getComment()
//


        );
    }
}


