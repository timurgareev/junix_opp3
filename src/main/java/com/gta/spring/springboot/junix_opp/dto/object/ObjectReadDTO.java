package com.gta.spring.springboot.junix_opp.dto.object;

import com.gta.spring.springboot.junix_opp.dto.project.ProjectReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class ObjectReadDTO{
    Integer id;
    String name;
    List<ProjectReadDTO> projects;
    }

