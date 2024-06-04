package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.scope.ScopeEditCreateDTO;
import com.gta.spring.springboot.junix_opp.servise.ScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/scope")
@RequiredArgsConstructor
public class ScopeController {

//    @Autowired
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

}
