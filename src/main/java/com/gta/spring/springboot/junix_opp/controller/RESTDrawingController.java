package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingWithRevisionsReadDTO;
import com.gta.spring.springboot.junix_opp.servise.DrawingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drawings")
@RequiredArgsConstructor
public class RESTDrawingController {

    @Autowired
    public DrawingService drawingService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DrawingWithRevisionsReadDTO> getAllDrawings() {
        return  drawingService.findAll();
    }

    @GetMapping("/{id}")
    public DrawingWithRevisionsReadDTO findById(@PathVariable("id") Long id) {
        return drawingService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/s")
    public List<DrawingReadDTO> getAllDrawingsOnly(){
        return drawingService.findAllSimple();
    }


//    @GetMapping("/search/{shifrString}")
//    public List<DrawingWithRevisionsReadDTO> getDrawingsByCodeNormalContaining(@PathVariable("shifrString") String shifrString) {
//        return drawingService.findDrawingsByCodeNormalContaining(shifrString);
//    }


}
