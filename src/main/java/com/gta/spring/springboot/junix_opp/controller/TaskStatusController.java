package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.taskStatusPrivate.TaskStatusPrivateReadDTO;
import com.gta.spring.springboot.junix_opp.dto.taskStatusPublic.TaskStatusPublicReadDTO;
import com.gta.spring.springboot.junix_opp.dto.unit.UnitReadDTO;
import com.gta.spring.springboot.junix_opp.servise.TaskStatusPrivateService;
import com.gta.spring.springboot.junix_opp.servise.TaskStatusPublicService;
import com.gta.spring.springboot.junix_opp.servise.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/taskstatus")
@RequiredArgsConstructor
public class TaskStatusController {

    private final TaskStatusPublicService taskStatusPublicService;
    private final TaskStatusPrivateService taskStatusPrivateService;

    @GetMapping("/public")
    public List<TaskStatusPublicReadDTO> getAllTaskStatusPublic() {
        return  taskStatusPublicService.findAll();
    }

    @GetMapping("/private")
    public List<TaskStatusPrivateReadDTO> getAllUnit() {
        return  taskStatusPrivateService.findAll();
    }

}
