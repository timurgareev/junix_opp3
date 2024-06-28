package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskWithSuppliesReadDTO;
import com.gta.spring.springboot.junix_opp.dto.typeOfWork.TypeOfWorkReadDTO;
import com.gta.spring.springboot.junix_opp.entity.TypeOfWork;
import com.gta.spring.springboot.junix_opp.repository.TypeOfWorkRepository;
import com.gta.spring.springboot.junix_opp.servise.TaskService;
import com.gta.spring.springboot.junix_opp.servise.TypeOfWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
