package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingWithRevisionsReadDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadMapper;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingWithRevisionReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.repository.DrawingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrawingService {

    @Autowired
    private DrawingRepository drawingRepository;

    @Autowired
    private DrawingWithRevisionReadMapper drawingWithRevisionReadMapper;

    @Autowired
    private DrawingReadMapper drawingReadMapper;

    private Boolean isArchive;
    private Boolean isOnDelete;

    public List<DrawingWithRevisionsReadDTO> findAll() {
        return drawingRepository.findAll().stream()
                .map(drawingWithRevisionReadMapper::map)
                .toList();
    }



    public Optional<DrawingWithRevisionsReadDTO> findById(Long id) {
        return drawingRepository.findById(id)
                .map(drawingWithRevisionReadMapper::map);
    }

//    public Optional<Drawing> findById0(Long id) {
//        return drawingRepository.findById(id);
//    }
    public Drawing findDrawingById(Long drawingId){
        return Optional.ofNullable(drawingId)
                .flatMap(drawingRepository::findById)
                .orElse(null);
    }

    public List<DrawingReadDTO> findAllSimple() {
        return drawingRepository.findAll().stream()
                .map(drawingReadMapper::map)
                .toList();
    }

    public List<DrawingReadDTO> findAllByProjectId(Integer projectId) {
        return drawingRepository.findAllByProjectId(projectId).stream()
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
