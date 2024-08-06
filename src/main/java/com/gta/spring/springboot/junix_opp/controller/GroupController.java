package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectEditDTO;
import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.servise.GroupService;
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
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    public final GroupService groupService;

    @GetMapping
    public List<GroupOfObjectReadDTO> getAllGroups() {
        return  groupService.findAll();
    }

    @GetMapping("/withobject")
    public List<GroupOfObjectWithChildrenReadDTO> getAllGroupsWithChildren() {
        return  groupService.findAllWithChildren();
    }

    @GetMapping("/{id}")
    public GroupOfObjectReadDTO findById(@PathVariable("id") Integer id) {
        return groupService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Group with Id=" + id + " not found"));
    }

    @GetMapping("/{id}/withobject")
    public GroupOfObjectWithChildrenReadDTO findByIdWithChildren(@PathVariable("id") Integer id) {
        return groupService.findByIdWithChildren(id)
                .orElseThrow(() -> new NoSuchElementException("Group with Id=" + id + " not found"));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody GroupOfObjectEditDTO editDTO,
                                    Principal principal, BindingResult bindingResult) throws BindException {

            if (bindingResult.hasErrors()) {
                    throw new BindException(bindingResult);
                }

        groupService.create(editDTO, principal);
        return new ResponseEntity<>("Group of object successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @Valid
            @PathVariable Integer id,
            @RequestBody GroupOfObjectEditDTO editDTO,
            @AuthenticationPrincipal Principal principal,
            BindingResult bindingResult) throws BindException {

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        groupService.update(id, editDTO, principal);
        return new ResponseEntity<>("Group of object with id=" + id + "successfully updates", HttpStatus.CREATED);
    }
    @PutMapping("/{id}/toggleisarchive")
    public ResponseEntity<?> setIsArchiveTrue(
            @PathVariable Integer id,
            @AuthenticationPrincipal Principal principal) {

        groupService.toggleIsArchive(id, principal);
        return new ResponseEntity<>("Set value isArchive with id=" + id + "successfully change", HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        groupService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("GroupOfObject with id=" + id + " deleted");
    }//добавить обработку ошибок на ненайденное айди
}
