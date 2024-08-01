package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.event.EventEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.event.EventReadDTO;
import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestReadDTO;
import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestWithTasksReadDTO;
import com.gta.spring.springboot.junix_opp.servise.SupplyRequestService;
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
@RequestMapping("/api/v1/supply")
@RequiredArgsConstructor
public class SupplyRequestController {

    private final SupplyRequestService supplyService;

    @PostMapping
    public ResponseEntity<String> createSupply(@RequestBody SupplyRequestCreateDTO supplyRequestCreateDTO, Principal principal) {
        supplyService.createSupply(supplyRequestCreateDTO, principal);
        return new ResponseEntity<>("SupplyRequest successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/{supplyId}")
    public ResponseEntity<String> updateSupply(@PathVariable Long supplyId,
                                              @RequestBody SupplyRequestCreateDTO supplyRequestCreateDTO,
                                              @AuthenticationPrincipal Principal principal) {
        supplyService.update(supplyId, supplyRequestCreateDTO, principal);
        return ResponseEntity.ok("SupplyRequest with id=" + supplyId +" object successfully updated");
    } //добавить обработку ошибок на ненайденное айди

    @DeleteMapping("/{supplyId}")
    public void deleteSupply(@PathVariable Long supplyId) {
        supplyService.deleteSupplyRequest(supplyId);
    }//добавить обработку ошибок на ненайденное айди


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SupplyRequestReadDTO> findAll() {
        return supplyService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<SupplyRequestReadDTO> supplyOptional = supplyService.findById(id);
        if (supplyOptional.isPresent()) {
            SupplyRequestReadDTO supply = supplyOptional.get();
            return ResponseEntity.ok(supply);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("заявка с id=" + id + " не найдена");
        }
    }

    @GetMapping("/drawing/{id}")
    public List<SupplyRequestReadDTO> findByDrawingId(@PathVariable("id") Long id) {
        return supplyService.findByDrawingId(id);
    }

    @GetMapping("/{id}/withtasks")
    public ResponseEntity<?> findByIdWithTasks(@PathVariable("id") Long id) {
        Optional<SupplyRequestWithTasksReadDTO> supplyOptional = supplyService.findByIdWithTasks(id);
        if (supplyOptional.isPresent()) {
            SupplyRequestWithTasksReadDTO supply = supplyOptional.get();
            return ResponseEntity.ok(supply);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("заявка с id=" + id + " не найдена");
        }
    }

    @GetMapping("/drawing/{id}/withtasks")
    public List<SupplyRequestWithTasksReadDTO> findByDrawingIdWithTasks(@PathVariable("id") Long id) {
        return supplyService.findByDrawingIdWithTasks(id);
    }

    @PostMapping("/task/{taskId}")
    public ResponseEntity<String> createNewSupplyWithTaskId(@PathVariable Long taskId,
                                                           @RequestBody SupplyRequestCreateDTO supplyDTOs,
                                                           Principal principal) {
        supplyService.createNewSupplyWithTaskId(taskId, supplyDTOs, principal);
        return new ResponseEntity<>("SupplyRequest with existing task successfully created",HttpStatus.CREATED);
    }




}

