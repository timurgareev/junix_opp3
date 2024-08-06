package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectEditDTO;
import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectEditDTO;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectReadDTO;
import com.gta.spring.springboot.junix_opp.servise.ObjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/objects")
@RequiredArgsConstructor
public class ObjectController {

    public final ObjectService objectService;

    @GetMapping
    public List<ObjectReadDTO> getAll() {
        return  objectService.findAll();
    }

    @GetMapping("/{id}")
    public ObjectReadDTO findById(@PathVariable("id") Integer id) {
        return objectService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Object with Id=" + id + " not found"));
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ObjectEditDTO editDTO,
                                    Principal principal, BindingResult bindingResult) throws BindException {

            if (bindingResult.hasErrors()) {
                    throw new BindException(bindingResult);
                }

        objectService.create(editDTO, principal);
        return new ResponseEntity<>("Object successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Valid
            @PathVariable Integer id,
            @RequestBody ObjectEditDTO editDTO,
            @AuthenticationPrincipal Principal principal,
            BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        objectService.update(id, editDTO, principal);
        return new ResponseEntity<>("Object with id=" + id + "successfully updates", HttpStatus.CREATED);
    }
    @PutMapping("/{id}/toggleisarchive")
    public ResponseEntity<?> setIsArchiveTrue(
            @PathVariable Integer id,
            @AuthenticationPrincipal Principal principal) {

        objectService.toggleIsArchive(id, principal);
        return new ResponseEntity<>("Set value isArchive with id=" + id + " successfully change", HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        objectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Object with id=" + id + " deleted");
    }
}
