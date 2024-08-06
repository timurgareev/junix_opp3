package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingEditDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionEditDTO;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.servise.RevisionService;
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
@RequestMapping("/api/v1/revisions")
@RequiredArgsConstructor
public class RevisionController {

    private final RevisionService revisionService;

    @GetMapping
    public List<RevisionReadDTO> getAll(
            @RequestParam(name="filterdrawingcode", required = false) String filterDrawingCode,
            @RequestParam(name="drawingid", required = false) Long drawingId,
            @RequestParam(name="islatest", required = false) Boolean isLatest,
            @RequestParam(name="isarchive", required = false) Boolean isArchive)

    {
        return  revisionService.findAllSearch(filterDrawingCode,drawingId,isLatest,isArchive);
    }

    @GetMapping("/{id}")
    public RevisionReadDTO findById(@PathVariable("id") Long id) {
        return revisionService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Revision with Id=" + id + " not found"));
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody RevisionEditDTO editDTO,
                                    Principal principal, BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        revisionService.create(editDTO, principal);
        return new ResponseEntity<>("Revision  successfully created", HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Valid
            @PathVariable Long id,
            @RequestBody RevisionEditDTO editDTO,
            @AuthenticationPrincipal Principal principal,
            BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        revisionService.update(id, editDTO, principal);
        return new ResponseEntity<>("Revision with id=" + id + "successfully updates", HttpStatus.CREATED);
    }
    @PutMapping("/{id}/toggleisarchive")
    public ResponseEntity<?> setIsArchiveTrue(
            @PathVariable Long id,
            @AuthenticationPrincipal Principal principal) {

        revisionService.toggleIsArchive(id, principal);
        return new ResponseEntity<>("Set value isArchive with id=" + id + " successfully change", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        revisionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Revision with id=" + id + " deleted");
    }

//    @GetMapping("/drawing/{id}")
//    public List<RevisionReadDTO> findAllByDrawingId(@PathVariable("id") Long id) {
//        return revisionService.findAllByDrawingId(id);
//
//    }


//    @GetMapping("/search/{shifrString}")
//    public List<DrawingWithRevisionsReadDTO> getDrawingsByCodeNormalContaining(@PathVariable("shifrString") String shifrString) {
//        return drawingService.findDrawingsByCodeNormalContaining(shifrString);
//    }


}
