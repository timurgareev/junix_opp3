package com.gta.spring.springboot.junix_opp.dto.drawing;


import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrawingReadMapper2 implements Mapper<Drawing, DrawingReadDTO2> {

    @Override
    public DrawingReadDTO2 map(Drawing object) {

        return new DrawingReadDTO2(
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
                object.getDrawingsMark().getId(),
                object.getDrawingsMark().getMarkkey(),
                object.getIsArchive(),
                object.getIsOnDelete()


        );
    }

}
