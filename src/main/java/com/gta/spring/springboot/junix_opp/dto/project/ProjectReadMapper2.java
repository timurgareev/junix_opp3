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
public class ProjectReadMapper2 implements Mapper<Project, ProjectReadDTO2> {

    @Override
    public ProjectReadDTO2 map(Project object) {
        return new ProjectReadDTO2(
                object.getId(),
                object.getName(),
                object.getFullname(),
                object.getCode(),
                object.getObject().getId(),
                object.getObject().getName(),
                object.getIsArchive(),
                object.getIsOnDelete()


        );
    }
}
