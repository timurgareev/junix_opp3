package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.project.ProjectReadDTO2;
import com.gta.spring.springboot.junix_opp.dto.unit.UnitReadDTO;
import com.gta.spring.springboot.junix_opp.servise.ProjectService;
import com.gta.spring.springboot.junix_opp.servise.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProjectReadDTO2> getAll() {
        return  projectService.findAll();
    }

}
