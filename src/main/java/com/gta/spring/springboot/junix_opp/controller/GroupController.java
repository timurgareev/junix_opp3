package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectReadDTO;
import com.gta.spring.springboot.junix_opp.servise.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public GroupOfObjectReadDTO findById(@PathVariable("id") Integer id) {
        return groupService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Group with Id=" + id + " not found"));
    }
}
