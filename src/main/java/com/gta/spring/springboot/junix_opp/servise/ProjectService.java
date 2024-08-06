package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.dto.drawing.projectTree.FlatDrawingDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.projectTree.FlatToGroupConverter;
import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.dto.project.*;
import com.gta.spring.springboot.junix_opp.entity.Object;
import com.gta.spring.springboot.junix_opp.entity.Project;
import com.gta.spring.springboot.junix_opp.exceptions.EntityAlreadyExistsException;
import com.gta.spring.springboot.junix_opp.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService implements BaseCRUDService<Project,
        ProjectReadDTO, ProjectEditDTO, Integer> {

    private final ProjectRepository projectRepository;
    private final ProjectReadMapper readMapper;
    private final ProjectEditMapper editMapper;

    @Override
    public Project findEntityById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(projectRepository::findById)
                .orElse(null);
    }

    @Override
    public List<ProjectReadDTO> findAll() {
        return projectRepository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }


    @Override
    public Optional<ProjectReadDTO> findById(Integer id) {
        return projectRepository.findById(id)
                .map(readMapper::map);
    }




    @Override
    @Transactional
    public void create(ProjectEditDTO editDTO, Principal principal) {
        if (projectRepository.existsByName(editDTO.getName())) {
            throw new EntityAlreadyExistsException("Project with name '" + editDTO.getName() + "' already exists.");
        }
        Project project = editMapper.map(editDTO);
        projectRepository.save(project);
    }


    @Override
    @Transactional
    public void update(Integer id, ProjectEditDTO editDTO, Principal principal) {
        Project project =  projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project with id '" + id + "' not found."));
        if (projectRepository.existsByNameAndNotId(editDTO.getName(),id)) {
            throw new EntityAlreadyExistsException("Project with name '" + editDTO.getName() + "' already exists.");
        }

        editMapper.map(editDTO, project);
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!projectRepository.existsById(id)) {
            throw new NoSuchElementException("Project with id '" + id + "' not found");
        }
        projectRepository.deleteById(id);
    }

    @Transactional
    public void toggleIsArchive(Integer id, Principal principal ) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project with id '" + id + "' not found."));
        project.setIsArchive(project.getIsArchive() == null || !project.getIsArchive());
        projectRepository.save(project);
    }



}
