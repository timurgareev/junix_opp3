package com.gta.spring.springboot.junix_opp.dto.project;

import com.gta.spring.springboot.junix_opp.dto.zone.ZoneReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class ProjectReadDTO {
    Integer id;
    String name;
    List<ZoneReadDTO> zones;
}