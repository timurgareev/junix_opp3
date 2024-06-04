package com.gta.spring.springboot.junix_opp.dto.object;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectReadMapper;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Object;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//до изменений по добавлению проектов
//@Component
//@RequiredArgsConstructor
//public class ObjectReadMapper implements Mapper<Object, ObjectReadDTO> {
//    @Override
//    public ObjectReadDTO map(Object object) {
//        return new ObjectReadDTO(
//                object.getId(),
//                object.getName()
//        );
//    }
//}

//после добавления Проектов
@Component
@RequiredArgsConstructor
public class ObjectReadMapper implements Mapper<Object, ObjectReadDTO> {

    @Autowired
    private ProjectReadMapper projectReadMapper;
    @Override
    public ObjectReadDTO map(Object object) {

        List<ProjectReadDTO> projectDTOlist = Optional.ofNullable(object.getProjects())
                .map(objects -> objects.stream()
                        .map(projectReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return new ObjectReadDTO(
                object.getId(),
                object.getName(),
                projectDTOlist
        );
    }
}