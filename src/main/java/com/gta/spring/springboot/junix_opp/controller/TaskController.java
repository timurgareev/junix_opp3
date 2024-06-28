package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.event.EventEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.event.EventReadDTO;
import com.gta.spring.springboot.junix_opp.dto.event.EventWithTasksReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskWithSuppliesReadDTO;
import com.gta.spring.springboot.junix_opp.servise.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskEditCreateDTO taskEditCreateDTO, Principal principal) {
        taskService.createTask(taskEditCreateDTO, principal);
        return new ResponseEntity<>("Task successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable Long taskId,
                                              @RequestBody TaskEditCreateDTO taskEditCreateDTO,
                                              @AuthenticationPrincipal Principal principal) {
        taskService.update(taskId, taskEditCreateDTO, principal);
        return ResponseEntity.ok("Task with id=" + taskId +" object successfully updated");
    } //добавить обработку ошибок на ненайденное айди

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }//добавить обработку ошибок на ненайденное айди

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

    @GetMapping("/drawing/{id}")
    public List<TaskReadDTO> findByDrawingId(@PathVariable("id") Long id) {
        return taskService.findByDrawingId(id);
    } //тут надо добавить обработку по ненайденным айди

    @GetMapping("/project/{id}")
    public List<TaskReadDTO> findByProjectId(@PathVariable("id") Integer id) {
        return taskService.findByProjectId(id);
    } //тут надо добавить обработку по ненайденным айди

    @GetMapping("/project/{id}/withsupplies")
    public List<TaskWithSuppliesReadDTO> findByProjectIdWithSupplies(@PathVariable("id") Integer id) {
        return taskService.findByProjectIdWithSupplies(id);
    } //тут надо добавить обработку по ненайденным айди

    @GetMapping("/{id}/withsupplies")
    public ResponseEntity<?> findByIdWithSupplies(@PathVariable("id") Long id) {
        Optional<TaskWithSuppliesReadDTO> taskOptional = taskService.findByIdWithSupplies(id);
        if (taskOptional.isPresent()) {
            TaskWithSuppliesReadDTO task = taskOptional.get();
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Task with id=" + id + " not found");
        }
    }

    @GetMapping("/drawing/{id}/withsupplies")
    public List<TaskWithSuppliesReadDTO> findByDrawingIdWithSupplies(@PathVariable("id") Long id) {
        return taskService.findByDrawingIdWithSupplies(id);
    } //тут не надо обработки так как выгрузится пустой лист если не найдет

    @PostMapping("/{taskId}/supply/{supplyId}")
    public ResponseEntity<String> linkTaskIdWithSupplyId(@PathVariable Long taskId,
                                                        @PathVariable Long supplyId) {
        taskService.linkTaskIdWithSupplyId(taskId, supplyId);
        return ResponseEntity.ok("Succesfully add link task with id="+ taskId+" with supply id="+supplyId);
    }//добавить обработку ошибок на ненайденное айди

    @DeleteMapping("/{taskId}/supply/{supplyId}")
    public void removeSupplyFromTask(@PathVariable Long taskId, @PathVariable Long supplyId) {
        taskService.removeSupplyFromTask(taskId, supplyId);
    }//добавить обработку ошибок на ненайденное айди

    @PostMapping("/event/{eventId}")
    public ResponseEntity<String> createNewTaskWithEventId(@PathVariable Long eventId,
                                                           @RequestBody TaskEditCreateDTO taskDTOs,
                                                           Principal principal) {
        taskService.createNewTaskWithEventId(eventId, taskDTOs, principal);
        return new ResponseEntity<>("Task with existing event successfully created",HttpStatus.CREATED);
    }

    @PostMapping("/supply/{supplyId}")
    public ResponseEntity<String> createNewTaskWithSupplyId(@PathVariable Long supplyId,
                                                           @RequestBody TaskEditCreateDTO taskDTOs,
                                                           Principal principal) {
        taskService.createNewTaskWithSupplyId(supplyId, taskDTOs, principal);
        return new ResponseEntity<>("Task with existing supply successfully created",HttpStatus.CREATED);
    }



}
