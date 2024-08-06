package com.gta.spring.springboot.junix_opp.dto.project;

import com.gta.spring.springboot.junix_opp.dto.zone.ZoneWithChildrenReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class ProjectWithChildrenReadDTO {
    Integer id;
    String name;
    Boolean isArchive;
    List<ZoneWithChildrenReadDTO> zones;
}