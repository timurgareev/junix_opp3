package com.gta.spring.springboot.junix_opp.controller;


import com.gta.spring.springboot.junix_opp.dto.project.ProjectEditDTO;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.servise.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectReadDTO> getAll() {
        return  projectService.findAll();
    }

//    @GetMapping("/ch")
//    public List<ProjectWithChildrenReadDTO> getAllWithChildren(
//            @RequestParam(name="id", required = false) Integer id
//    ) {
//        return  projectService.findAllWithChildren(id);
//    }

//    @GetMapping("/ch")
//    public ProjectWithChildrenReadDTO getAllWithChildren(
//            @RequestParam(name="id", required = false) Integer id
//    ) {
//        return  projectService.findAllWithChildren(id)
//                .orElseThrow(() -> new NoSuchElementException("Project with Id=" + id + " not found"));
//    }

    @GetMapping("/{id}")
    public ProjectReadDTO findById(@PathVariable("id") Integer id) {
        return projectService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Project with Id=" + id + " not found"));
    }




    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProjectEditDTO editDTO,
                                    Principal principal, BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        projectService.create(editDTO, principal);
        return new ResponseEntity<>("Project of object successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Valid
            @PathVariable Integer id,
            @RequestBody ProjectEditDTO editDTO,
            @AuthenticationPrincipal Principal principal,
            BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        projectService.update(id, editDTO, principal);
        return new ResponseEntity<>("Project with id=" + id + "successfully updates", HttpStatus.CREATED);
    }
    @PutMapping("/{id}/toggleisarchive")
    public ResponseEntity<?> setIsArchiveTrue(
            @PathVariable Integer id,
            @AuthenticationPrincipal Principal principal) {

        projectService.toggleIsArchive(id, principal);
        return new ResponseEntity<>("Set value isArchive with id=" + id + " successfully change", HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        projectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Project with id=" + id + " deleted");
    }
}
