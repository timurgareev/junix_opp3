package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.dto.drawing.*;
import com.gta.spring.springboot.junix_opp.dto.drawing.projectTree.FlatDrawingDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.projectTree.FlatToGroupConverter;
import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import com.gta.spring.springboot.junix_opp.exceptions.EntityAlreadyExistsException;
import com.gta.spring.springboot.junix_opp.repository.DrawingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrawingService implements BaseCRUDService<Drawing,
        DrawingReadDTO, DrawingEditDTO, Long> {

    private final DrawingRepository drawingRepository;
    private final DrawingReadMapper readMapper;
    private final DrawingEditMapper editMapper;
    private final DrawingLightReadMapper lightReadMapper;
    private final DrawingMaxInfoReadMapper maxInfoReadMapper;
    private final FlatToGroupConverter flatToGroupConverter;

    @Override
    public Drawing findEntityById(Long drawingId){
        return Optional.ofNullable(drawingId)
                .flatMap(drawingRepository::findById)
                .orElse(null);
    }

    @Override
    public List<DrawingReadDTO> findAll() {
            return drawingRepository.findAll().stream()
                    .map(readMapper::map)
                    .toList();
        }

    public List<DrawingLightReadDTO> findAllLight() {
        return drawingRepository.findAll().stream()
                .map(lightReadMapper::map)
                .toList();
    }

    public List<DrawingMaxInfoReadDTO> findAllMaxInfo() {
        return drawingRepository.findAll().stream()
                .map(maxInfoReadMapper::map)
                .toList();
    }

    public List<DrawingReadDTO> findAllSearch(
            String filterCode, Integer projectId, Integer zoneId, Boolean isArchive) {
            List<Drawing> drawings = drawingRepository.findAllSearch(filterCode, projectId, zoneId, isArchive);
            return drawings.stream()
                    .map(readMapper::map)
                    .toList();
    }

    public List<DrawingLightReadDTO> findAllSearchLight(
            String filterCode, Integer projectId, Integer zoneId, Boolean isArchive) {
        List<Drawing> drawings = drawingRepository.findAllSearch(filterCode, projectId, zoneId, isArchive);
        return drawings.stream()
                .map(lightReadMapper::map)
                .toList();
    }

    public List<DrawingMaxInfoReadDTO> findAllSearchMaxInfo(
            String filterCode, Integer projectId, Integer zoneId, Boolean isArchive) {
        List<Drawing> drawings = drawingRepository.findAllSearch(filterCode, projectId, zoneId, isArchive);
        return drawings.stream()
                .map(maxInfoReadMapper::map)
                .toList();
    }

    @Transactional
    public GroupOfObjectWithChildrenReadDTO getGroupOfObjectWithChildren(Integer groupId) {
        List<FlatDrawingDTO> flatDrawings = drawingRepository.findFlatDrawingsByGroupId(groupId);

        // Преобразование списка FlatDrawingDTO в один объект GroupOfObjectWithChildrenReadDTO
        return flatToGroupConverter.convert(flatDrawings);
    }

    @Override
    public Optional<DrawingReadDTO> findById(Long id) {
        return drawingRepository.findById(id)
                .map(readMapper::map);
    }

    public Optional<DrawingLightReadDTO> findByIdLight(Long id) {
        return drawingRepository.findById(id)
                .map(lightReadMapper::map);
    }

    public Optional<DrawingMaxInfoReadDTO> findByIdMaxInfo(Long id) {
        return drawingRepository.findById(id)
                .map(maxInfoReadMapper::map);
    }

    @Override
    @Transactional
    public void create(DrawingEditDTO editDTO, Principal principal) {
        if (drawingRepository.existsByCode(editDTO.getCode())) {
            throw new EntityAlreadyExistsException("Project with code '" + editDTO.getCode() + "' already exists");
        }
        Drawing drawing = editMapper.map(editDTO);
        drawingRepository.save(drawing);
    }

    @Override
    @Transactional
    public void update(Long id, DrawingEditDTO editDTO, Principal principal) {
        Drawing drawing =  drawingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Drawing with id '" + id + "' not found."));
        if (drawingRepository.existsByCodeAndNotId(editDTO.getCode(),id)) {
            throw new EntityAlreadyExistsException("Drawing with code '" + editDTO.getCode() + "' already exists");
        }
        editMapper.map(editDTO, drawing);
        drawingRepository.save(drawing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!drawingRepository.existsById(id)) {
            throw new NoSuchElementException("Drawing with id '" + id + "' not found");
        }
        drawingRepository.deleteById(id);
    }

    @Transactional
    public void toggleIsArchive(Long id, Principal principal ) {
        Drawing drawing = drawingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Drawing with id '" + id + "' not found."));
        drawing.setIsArchive(drawing.getIsArchive() == null || !drawing.getIsArchive());
        drawingRepository.save(drawing);
    }




//    public List<DrawingReadDTO> findAllByProjectId(Integer projectId) {
//        return drawingRepository.findAllByProjectId(projectId).stream()
//                .map(readMapper::map)
//                .toList();
//    }
//    public List<DrawingLightReadDTO> findAllLightByProjectId(Integer projectId) {
//        return drawingRepository.findAllByProjectId(projectId).stream()
//                .map(lightReadMapper::map)
//                .toList();
//    }
//    public List<DrawingMaxInfoReadDTO> findAllMaxInfoByProjectId(Integer projectId) {
//        return drawingRepository.findAllByProjectId(projectId).stream()
//                .map(maxInfoReadMapper::map)
//                .toList();
//    }
//    public List<DrawingReadDTO> findDrawingsByCodeContaining(String shifrString) {
//        List<Drawing> drawings = drawingRepository.findByCodeContaining(shifrString);
//        return drawings.stream()
//                .map(readMapper::map)
//                .toList();
//    }
//    public List<DrawingLightReadDTO> findDrawingsByCodeContainingLight(String shifrString) {
//        List<Drawing> drawings = drawingRepository.findByCodeContaining(shifrString);
//        return drawings.stream()
//                .map(lightReadMapper::map)
//                .toList();
//    }
//    public List<DrawingMaxInfoReadDTO> findDrawingsByCodeContainingMaxInfo(String shifrString) {
//        List<Drawing> drawings = drawingRepository.findByCodeContaining(shifrString);
//        return drawings.stream()
//                .map(maxInfoReadMapper::map)
//                .toList();
//    }

}
