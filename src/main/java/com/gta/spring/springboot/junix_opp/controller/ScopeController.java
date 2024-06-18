package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.event.EventReadDTO;
import com.gta.spring.springboot.junix_opp.dto.scope.ScopeEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.scope.ScopeReadDTO;
import com.gta.spring.springboot.junix_opp.servise.ScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/scope")
@RequiredArgsConstructor
public class ScopeController {


    public final ScopeService scopeService;

//    @PostMapping - этот выводит объект в ответе а он большой, заменил его на строку
//    public ResponseEntity<Scope> createScope(@RequestBody ScopeEditCreateDTO scopeEditCreateDTO, Principal principal) {
//        Scope createdScope = scopeService.createScope(scopeEditCreateDTO, principal);
//        return new ResponseEntity<>(createdScope, HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<String> createScope(@RequestBody ScopeEditCreateDTO scopeEditCreateDTO, Principal principal) {
        scopeService.createScope(scopeEditCreateDTO, principal);
        return new ResponseEntity<>("Scope successfully created", HttpStatus.CREATED);
    }


    @PutMapping("/{scopeId}")
    public ResponseEntity<String> updateScope(@PathVariable Long scopeId,
                                            @RequestBody ScopeEditCreateDTO scopeEditCreateDTO,
                                            @AuthenticationPrincipal Principal principal) {
        scopeService.update(scopeId, scopeEditCreateDTO, principal);
        return ResponseEntity.ok("Scope object successfully updated");
    }//добавить обработку ошибок на ненайденное айди
    @DeleteMapping("/{scopeId}")
    public void deleteEvent(@PathVariable Long scopeId) {
        scopeService.deleteScope(scopeId);
    }

    @GetMapping("/drawing/{id}")
    public List<ScopeReadDTO> findByDrawingId(@PathVariable("id") Long id) {
        return scopeService.findByDrawingId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<ScopeReadDTO> scopeOptional = scopeService.findById(id);
        if (scopeOptional.isPresent()) {
            ScopeReadDTO scope = scopeOptional.get();
            return ResponseEntity.ok(scope);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Объемов с id=" + id + " не найдено");
        }
    }

}
