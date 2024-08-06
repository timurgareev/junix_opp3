package com.gta.spring.springboot.junix_opp.dto.drawing;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.servise.DrawingMarkService;
import com.gta.spring.springboot.junix_opp.servise.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrawingEditMapper implements Mapper<DrawingEditDTO, Drawing> {

    private final ZoneService zoneService;
    private final DrawingMarkService drawingMarkService;
    @Override
    public Drawing map(DrawingEditDTO object) {
        Drawing drawing = new Drawing();
        copy(object,drawing);
        return drawing;
    }

    @Override
    public Drawing map(DrawingEditDTO fromObject, Drawing toObject) {
        copy(fromObject,toObject);
        return toObject;
    }
    private void copy(DrawingEditDTO object, Drawing drawing){
        drawing.setCode(object.getCode());
        drawing.setName(object.getName());
        drawing.setZone(zoneService.findEntityById(object.getZoneId()));
        drawing.setState(object.getState());
        drawing.setDescription(object.getDescription());
        drawing.setCodestring(object.getCodeString());
        drawing.setMarkDrawingKey(object.getMarkDrawingKey());
        drawing.setDrawingsMark(drawingMarkService.findDrawingMarkById(object.getDrawingMarksId()));

    }

}
