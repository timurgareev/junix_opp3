package com.gta.spring.springboot.junix_opp.dto.project;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneWithChildrenReadMapper;
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
public class ProjectWithChildrenReadMapper implements Mapper<Project, ProjectWithChildrenReadDTO> {

    @Autowired
    private ZoneWithChildrenReadMapper zoneReadMapper;

    @Override
    public ProjectWithChildrenReadDTO map(Project object) {
        List<ZoneWithChildrenReadDTO> zonesDTOlist = Optional.ofNullable(object.getZones())
                .map(objects -> objects.stream()
                        .map(zoneReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return new ProjectWithChildrenReadDTO(
                object.getId(),
                object.getName(),
                object.getIsArchive(),
                zonesDTOlist
        );
    }
}
