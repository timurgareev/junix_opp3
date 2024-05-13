package com.gta.spring.springboot.junix_opp.dto.mapping;

import com.gta.spring.springboot.junix_opp.dto.dto.*;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DrawingWithRevisionReadMapper implements Mapper<Drawing, DrawingWithRevisionsReadDTO> {

    @Autowired
    private  DrawingMarkReadMapper drawingMarkReadMapper;
    @Autowired
    private  RevisionReadMapper revisionReadMapper;

    @Override
    public DrawingWithRevisionsReadDTO map(Drawing object) {

        DrawingMarkReadDTO drawingMarkReadDTO = Optional.ofNullable(object.getDrawingsMark())
                .map(drawingMarkReadMapper::map)
                .orElse(null);

        List<RevisionReadDTO> revisionsDTOList = Optional.ofNullable(object.getRevisions())
                .map(objects -> objects.stream()
                        .map(revisionReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return new DrawingWithRevisionsReadDTO(
                object.getId(),
                object.getCode(),
                object.getName(),
                object.getState(),
                object.getDescription(),
                object.getCodestring(),
                object.getMarkDrawingKey(),
                drawingMarkReadDTO,
                revisionsDTOList
        );
    }

}
