package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingWithRevisionsReadDTO;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.servise.DrawingService;
import com.gta.spring.springboot.junix_opp.servise.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/revisions")
@RequiredArgsConstructor
public class RevisionController {

    private final RevisionService revisionService;
    @GetMapping("/drawing/{id}")
    public List<RevisionReadDTO> findAllByDrawingId(@PathVariable("id") Long id) {
        return revisionService.findAllByDrawingId(id);

    }


//    @GetMapping("/search/{shifrString}")
//    public List<DrawingWithRevisionsReadDTO> getDrawingsByCodeNormalContaining(@PathVariable("shifrString") String shifrString) {
//        return drawingService.findDrawingsByCodeNormalContaining(shifrString);
//    }


}
