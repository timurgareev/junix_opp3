package com.gta.spring.springboot.junix_opp.dto.project;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneReadDTO;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectReadMapper implements Mapper<Project, ProjectReadDTO> {

    @Autowired
    private ZoneReadMapper zoneReadMapper;

    @Override
    public ProjectReadDTO map(Project object) {
        List<ZoneReadDTO> zonesDTOlist = Optional.ofNullable(object.getZones())
                .map(objects -> objects.stream()
                        .map(zoneReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return new ProjectReadDTO(
                object.getId(),
                object.getName(),
                zonesDTOlist
        );
    }
}
