package com.gta.spring.springboot.junix_opp.dto.object;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Object;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ObjectReadMapper implements Mapper<Object, ObjectReadDTO> {

    @Override
    public ObjectReadDTO map(Object object) {

        return new ObjectReadDTO(
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