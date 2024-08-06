package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingEditDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingLightReadDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingMaxInfoReadDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneEditDTO;
import com.gta.spring.springboot.junix_opp.servise.DrawingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/drawings")
@RequiredArgsConstructor
public class DrawingController {

    public final DrawingService drawingService;

    @GetMapping
    public List<DrawingReadDTO> getAll(
            @RequestParam(name="filtercode", required = false) String filterCode,
            @RequestParam(name="projectid", required = false) Integer projectId,
            @RequestParam(name="zoneid", required = false) Integer zoneId,
            @RequestParam(name="isarchive", required = false) Boolean isArchive)

     {
        return  drawingService.findAllSearch(filterCode,projectId,zoneId,isArchive);
    }
    @GetMapping("/l")
    public List<DrawingLightReadDTO> getAllLight(
        @RequestParam(name="filtercode", required = false) String filterCode,
        @RequestParam(name="projectid", required = false) Integer projectId,
        @RequestParam(name="zoneid", required = false) Integer zoneId,
        @RequestParam(name="isarchive", required = false) Boolean isArchive)
        {
            return  drawingService.findAllSearchLight(filterCode,projectId,zoneId,isArchive);
        }

    @GetMapping("/m")
    public List<DrawingMaxInfoReadDTO> getAllMaxInfo(
        @RequestParam(name="filtercode", required = false) String filterCode,
        @RequestParam(name="projectid", required = false) Integer projectId,
        @RequestParam(name="zoneid", required = false) Integer zoneId,
        @RequestParam(name="isarchive", required = false) Boolean isArchive)
        {
            return  drawingService.findAllSearchMaxInfo(filterCode,projectId,zoneId,isArchive);
    }

    @GetMapping("/tree")
    public ResponseEntity<GroupOfObjectWithChildrenReadDTO> getGroupOfObjectWithChildren(
            @RequestParam(name = "groupid") Integer groupId) {
        try {
            GroupOfObjectWithChildrenReadDTO result = drawingService.getGroupOfObjectWithChildren(groupId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            // Логирование и обработка исключений могут быть добавлены здесь
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public DrawingReadDTO findById(@PathVariable("id") Long id) {
        return drawingService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Drawing with Id=" + id + " not found"));
    }
    @GetMapping("/{id}/l")
    public DrawingLightReadDTO findByIdLight(@PathVariable("id") Long id) {
        return drawingService.findByIdLight(id)
                .orElseThrow(() -> new NoSuchElementException("Drawing with Id=" + id + " not found"));
    }
    @GetMapping("/{id}/m")
    public DrawingMaxInfoReadDTO findByIdMaxInfo(@PathVariable("id") Long id) {
        return drawingService.findByIdMaxInfo(id)
                .orElseThrow(() -> new NoSuchElementException("Drawing with Id=" + id + " not found"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DrawingEditDTO editDTO,
                                    Principal principal, BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        drawingService.create(editDTO, principal);
        return new ResponseEntity<>("Drawing  successfully created", HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Valid
            @PathVariable Long id,
            @RequestBody DrawingEditDTO editDTO,
            @AuthenticationPrincipal Principal principal,
            BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        drawingService.update(id, editDTO, principal);
        return new ResponseEntity<>("Drawing with id=" + id + "successfully updates", HttpStatus.CREATED);
    }
    @PutMapping("/{id}/toggleisarchive")
    public ResponseEntity<?> setIsArchiveTrue(
            @PathVariable Long id,
            @AuthenticationPrincipal Principal principal) {

        drawingService.toggleIsArchive(id, principal);
        return new ResponseEntity<>("Set value isArchive with id=" + id + " successfully change", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        drawingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Drawing with id=" + id + " deleted");
    }

//    @GetMapping("/project/{id}")
//    public List<DrawingReadDTO> findAllByProjectId(@PathVariable("id") Integer id) {
//        return drawingService.findAllByProjectId(id);
//
//    }
//    @GetMapping("/project/{id}/l")
//    public List<DrawingLightReadDTO> findAllByProjectIdMaxInfo(@PathVariable("id") Integer id) {
//        return drawingService.findAllLightByProjectId(id);
//
//    }
//    @GetMapping("/project/{id}/m")
//    public List<DrawingMaxInfoReadDTO> findAllByProjectIdLight(@PathVariable("id") Integer id) {
//        return drawingService.findAllMaxInfoByProjectId(id);
//
//    }

//    @GetMapping("/search/{str}")
//    public List<DrawingReadDTO> findAllByCodeContaining(@PathVariable("str") String str) {
//        return drawingService.findDrawingsByCodeContaining(str);
//
//    }
//    @GetMapping("/search/{str}/l")
//    public List<DrawingLightReadDTO> findAllByCodeContainingMaxInfo(@PathVariable("str") String str) {
//        return drawingService.findDrawingsByCodeContainingLight(str);
//
//    }
//    @GetMapping("/search/{str}/m")
//    public List<DrawingMaxInfoReadDTO> findAllByCodeContainingLight(@PathVariable("str") String str) {
//        return drawingService.findDrawingsByCodeContainingMaxInfo(str);
//
//    }



}
