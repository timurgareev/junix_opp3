package com.gta.spring.springboot.junix_opp.dto.drawing;


import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrawingReadMapper implements Mapper<Drawing, DrawingReadDTO> {

    @Override
    public DrawingReadDTO map(Drawing object) {

        return new DrawingReadDTO(
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
                object.getIsArchive(),
                object.getIsOnDelete()


        );
    }

}
