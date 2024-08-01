package com.gta.spring.springboot.junix_opp.dto.object;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectReadMapper;
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
public class Object2ReadMapper implements Mapper<Object, Object2ReadDTO> {

    @Override
    public Object2ReadDTO map(Object object) {

        return new Object2ReadDTO(
                object.getId(),
                object.getName(),
                object.getFullname(),
                object.getComment(),
                object.getOrdernumber(),
                object.getGroup_of_objects().getId(),
                object.getGroup_of_objects().getName(),
                object.getIsArchive(),
                object.getIsOnDelete()
        );
    }
}