package com.gta.spring.springboot.junix_opp.dto.project;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Project;
import com.gta.spring.springboot.junix_opp.servise.ObjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectEditMapper implements Mapper<ProjectEditDTO, Project> {

    private final ObjectService objectService;

    @Override
    public Project map(ProjectEditDTO object) {
        Project project = new Project();
        copy(object, project);
        return project;
    }

    @Override
    public Project map(ProjectEditDTO fromObject, Project toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ProjectEditDTO object, Project project) {
        project.setName(object.getName());
        project.setFullname(object.getFullname());
        project.setCode(object.getCode());
        project.setOrdernumber(object.getOrdernumber());
        project.setDeclaration(object.getDeclaration());
        project.setObject(objectService.findEntityById(object.getObjectId()));
//        project.setIsArchive(object.getIsArchive());
//        project.setIsOnDelete(object.getIsOnDelete());
    }
}
