package com.gta.spring.springboot.junix_opp.dto.object;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectWithChildrenReadMapper;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Object;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ObjectWithChildrenReadMapper implements Mapper<Object, ObjectWithChildrenReadDTO> {

    @Autowired
    private ProjectWithChildrenReadMapper projectReadMapper;
    @Override
    public ObjectWithChildrenReadDTO map(Object object) {

        List<ProjectWithChildrenReadDTO> projectDTOlist = Optional.ofNullable(object.getProjects())
                .map(objects -> objects.stream()
                        .map(projectReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return new ObjectWithChildrenReadDTO(
                object.getId(),
                object.getName(),
                object.getIsArchive(),
                projectDTOlist
        );
    }
}