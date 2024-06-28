package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.typeOfWork.TypeOfWorkReadDTO;
import com.gta.spring.springboot.junix_opp.dto.unit.UnitReadDTO;
import com.gta.spring.springboot.junix_opp.servise.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/unit")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UnitReadDTO> getAllUnit() {
        return  unitService.findAll();
    }

}
