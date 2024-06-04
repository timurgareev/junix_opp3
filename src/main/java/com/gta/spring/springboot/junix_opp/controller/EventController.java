package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.event.EventEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.event.EventReadDTO;
import com.gta.spring.springboot.junix_opp.servise.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    public final EventService eventService;

    @GetMapping("/{id}")
    public EventReadDTO findById(@PathVariable("id") Long id) {
        return eventService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventEditCreateDTO eventEditCreateDTO, Principal principal) {
        eventService.creareEvent(eventEditCreateDTO, principal);
        return new ResponseEntity<>("Events successfully created", HttpStatus.CREATED);
    }
}
