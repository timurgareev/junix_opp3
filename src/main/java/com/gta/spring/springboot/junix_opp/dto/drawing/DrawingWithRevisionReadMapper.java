package com.gta.spring.springboot.junix_opp.dto.drawing;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadMapper;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
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
public class DrawingWithRevisionReadMapper implements Mapper<Drawing, DrawingWithRevisionsReadDTO> {

    @Autowired
    private DrawingMarkReadMapper drawingMarkReadMapper;
    @Autowired
    private RevisionReadMapper revisionReadMapper;
    private  final ScopeReadMapper scopeReadMapper;

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

        List<ScopeReadDTO> scopeReadDTOList = Optional.ofNullable(object.getScopes())
                .map(objects -> objects.stream()
                        .map(scopeReadMapper::map)
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
                revisionsDTOList,
                scopeReadDTOList
        );
    }

}
