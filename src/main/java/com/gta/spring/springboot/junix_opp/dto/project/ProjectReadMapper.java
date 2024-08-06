package com.gta.spring.springboot.junix_opp.dto.project;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectReadMapper implements Mapper<Project, ProjectReadDTO> {

    @Override
    public ProjectReadDTO map(Project object) {
        return new ProjectReadDTO(
                object.getId(),
                object.getName(),
                object.getFullname(),
                object.getCode(),
                object.getOrdernumber(),
                object.getDeclaration(),
                object.getObject().getId(),
                object.getObject().getName(),
                object.getIsArchive(),
                object.getIsOnDelete()


        );
    }
}
