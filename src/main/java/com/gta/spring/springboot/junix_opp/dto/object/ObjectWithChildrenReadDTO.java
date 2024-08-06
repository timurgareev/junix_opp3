package com.gta.spring.springboot.junix_opp.dto.object;

import com.gta.spring.springboot.junix_opp.dto.project.ProjectWithChildrenReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class ObjectWithChildrenReadDTO {
    Integer id;
    String name;
    Boolean isArchive;
    List<ProjectWithChildrenReadDTO> projects;

    }

