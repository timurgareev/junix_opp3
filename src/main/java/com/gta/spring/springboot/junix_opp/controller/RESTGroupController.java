package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectReadDTO;
import com.gta.spring.springboot.junix_opp.servise.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class RESTGroupController {

    @Autowired
    public GroupService groupService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupOfObjectReadDTO> getAllGroups() {
        return  groupService.findAll();
    }

    @GetMapping("/{id}")
    public GroupOfObjectReadDTO findById(@PathVariable("id") Integer id) {
        return groupService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
