package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.typeOfWork.TypeOfWorkReadDTO;
import com.gta.spring.springboot.junix_opp.servise.TypeOfWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/typeofwork")
@RequiredArgsConstructor
public class TypeOfWorkController {

private final TypeOfWorkService typeOfWorkService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeOfWorkReadDTO> getAllTypeOfWork() {
        return  typeOfWorkService.findAll();
    }


}
