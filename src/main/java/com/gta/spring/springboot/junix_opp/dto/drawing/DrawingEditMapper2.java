package com.gta.spring.springboot.junix_opp.dto.drawing;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.event.EventEditCreateDTO;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.entity.DrawingsMark;
import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.servise.DrawingMarkService;
import com.gta.spring.springboot.junix_opp.servise.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrawingEditMapper2 implements Mapper<DrawingEditDTO2, Drawing> {

    private final ZoneService zoneService;
    private final DrawingMarkService drawingMarkService;
    @Override
    public Drawing map(DrawingEditDTO2 object) {
        Drawing drawing = new Drawing();
        copy(object,drawing);
        return drawing;
    }

    @Override
    public Drawing map(DrawingEditDTO2 fromObject, Drawing toObject) {
        copy(fromObject,toObject);
        return toObject;
    }
    private void copy(DrawingEditDTO2 object, Drawing drawing){
        drawing.setCode(object.getCode());
        drawing.setName(object.getName());

        drawing.setZone(zoneService.findZoneById(object.getZoneId()));
        drawing.setState(object.getState());
        drawing.setDescription(object.getDescription());
        drawing.setCodestring(object.getCodeString());
        drawing.setMarkDrawingKey(object.getMarkDrawingKey());
        drawing.setDrawingsMark(drawingMarkService.findDrawingMarkById(object.getDrawingMarksId()));
        drawing.setIsArchive(object.getIsArchive());
        drawing.setIsOnDelete(object.getIsOnDelete());
    }

}
