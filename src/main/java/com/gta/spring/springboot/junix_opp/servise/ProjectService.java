package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectReadDTO2;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectReadMapper2;
import com.gta.spring.springboot.junix_opp.entity.Project;
import com.gta.spring.springboot.junix_opp.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectReadMapper2 projectReadMapper2;

    public Project findProjectById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(projectRepository::findById)
                .orElse(null);
    }

    public List<ProjectReadDTO2> findAll() {
        return projectRepository.findAll().stream()
                .map(projectReadMapper2::map)
                .toList();
    }

}
