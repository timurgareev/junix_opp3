package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadMapper;
import com.gta.spring.springboot.junix_opp.entity.DrawingsMark;
import com.gta.spring.springboot.junix_opp.entity.Revision;
import com.gta.spring.springboot.junix_opp.repository.DrawingMarkRepository;
import com.gta.spring.springboot.junix_opp.repository.RevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrawingMarkService {

private final DrawingMarkRepository drawingMarkRepository;
    public DrawingsMark findDrawingMarkById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(drawingMarkRepository::findById)
                .orElse(null);
    }


}
