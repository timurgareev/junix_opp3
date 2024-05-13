package com.gta.spring.springboot.junix_opp.dto.mapping;

import com.gta.spring.springboot.junix_opp.dto.dto.ObjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.dto.ProjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.dto.ZoneReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Object;
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
