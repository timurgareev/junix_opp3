package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.event.EventReadDTO;
import com.gta.spring.springboot.junix_opp.dto.event.EventWithTasksReadDTO;
import com.gta.spring.springboot.junix_opp.dto.unit.UnitReadDTO;
import com.gta.spring.springboot.junix_opp.dto.user.UserReadDTO;
import com.gta.spring.springboot.junix_opp.servise.UnitService;
import com.gta.spring.springboot.junix_opp.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserReadDTO> getAllUsers() {
        return  userService.findAll();
    }

    @GetMapping("/current")
    public UserReadDTO findUserIdByPrincipal(Principal principal) {
        return userService.getUserDTOByPrincipal(principal);
        }



}
