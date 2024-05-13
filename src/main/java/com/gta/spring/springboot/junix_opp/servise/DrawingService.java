package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.dto.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.dto.DrawingWithRevisionsReadDTO;
import com.gta.spring.springboot.junix_opp.dto.dto.GroupOfObjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.mapping.DrawingReadMapper;
import com.gta.spring.springboot.junix_opp.dto.mapping.DrawingWithRevisionReadMapper;
import com.gta.spring.springboot.junix_opp.dto.mapping.GroupOfObjectReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.repository.DrawingRepository;
import com.gta.spring.springboot.junix_opp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrawingService {

    @Autowired
    private DrawingRepository drawingRepository;

    @Autowired
    private DrawingWithRevisionReadMapper drawingWithRevisionReadMapper;

    @Autowired
    private DrawingReadMapper drawingReadMapper;


    public List<DrawingWithRevisionsReadDTO> findAll() {
        return drawingRepository.findAll().stream()
                .map(drawingWithRevisionReadMapper::map)
                .toList();
    }

    public Optional<DrawingWithRevisionsReadDTO> findById(Long id) {
        return drawingRepository.findById(id)
                .map(drawingWithRevisionReadMapper::map);
    }

    public List<DrawingReadDTO> findAllSimple() {
        return drawingRepository.findAll().stream()
                .map(drawingReadMapper::map)
                .toList();
    }


//    public List<DrawingWithRevisionsReadDTO> findDrawingsByCodeNormalContaining(String shifrString) {
//        List<Drawing> drawings = drawingRepository.findByCodeContaining(shifrString);
//        return drawings.stream()
//                .map(drawingWithRevisionReadMapper::map)
//                .collect(Collectors.toList());
//    }


}
