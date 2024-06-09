package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.entity.Project;
import com.gta.spring.springboot.junix_opp.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project findProjectById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(projectRepository::findById)
                .orElse(null);
    }

}
