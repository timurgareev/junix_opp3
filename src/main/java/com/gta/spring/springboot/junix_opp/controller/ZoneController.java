package com.gta.spring.springboot.junix_opp.controller;




import com.gta.spring.springboot.junix_opp.dto.zone.ZoneEditDTO;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneReadDTO;
import com.gta.spring.springboot.junix_opp.servise.ZoneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/zone")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ZoneReadDTO> getAll() {
        return  zoneService.findAll();
    }

    @GetMapping("/{id}")
    public ZoneReadDTO findById(@PathVariable("id") Integer id) {
        return zoneService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Zone with Id=" + id + " not found"));
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ZoneEditDTO editDTO,
                                    Principal principal, BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        zoneService.create(editDTO, principal);
        return new ResponseEntity<>("Zone successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Valid
            @PathVariable Integer id,
            @RequestBody ZoneEditDTO editDTO,
            @AuthenticationPrincipal Principal principal,
            BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        zoneService.update(id, editDTO, principal);
        return new ResponseEntity<>("Zone with id=" + id + "successfully updates", HttpStatus.CREATED);
    }
    @PutMapping("/{id}/toggleisarchive")
    public ResponseEntity<?> setIsArchiveTrue(
            @PathVariable Integer id,
            @AuthenticationPrincipal Principal principal) {

        zoneService.toggleIsArchive(id, principal);
        return new ResponseEntity<>("Set value isArchive with id=" + id + " successfully change", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        zoneService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Zone with id=" + id + " deleted");
    }
}
