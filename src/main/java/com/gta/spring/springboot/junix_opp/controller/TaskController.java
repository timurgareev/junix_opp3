package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.event.EventEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.event.EventReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import com.gta.spring.springboot.junix_opp.servise.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<TaskReadDTO> taskOptional = taskService.findById(id);
        if (taskOptional.isPresent()) {
            TaskReadDTO task = taskOptional.get();
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Task with id=" + id + " not found");
        }
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskEditCreateDTO taskEditCreateDTO, Principal principal) {
        taskService.createTask(taskEditCreateDTO, principal);
        return new ResponseEntity<>("Task successfully created", HttpStatus.CREATED);
    }


    @GetMapping("/drawing/{id}")
    public List<TaskReadDTO> findByDrawingId(@PathVariable("id") Long id) {
        return taskService.findByDrawingId(id);
    } //тут надо добавить обработку по ненайденным айди

    @GetMapping("/project/{id}")
    public List<TaskReadDTO> findByProjectId(@PathVariable("id") Integer id) {
        return taskService.findByProjectId(id);
    } //тут надо добавить обработку по ненайденным айди


}
