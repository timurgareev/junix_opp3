package com.gta.spring.springboot.junix_opp.dto.object;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.object.Object2EditDTO;
import com.gta.spring.springboot.junix_opp.entity.Object;
import com.gta.spring.springboot.junix_opp.servise.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Object2EditMapper implements Mapper<Object2EditDTO, Object> {

    private final GroupService groupService;

    @Override
    public Object map(Object2EditDTO object) {
        Object objectEntity = new Object();
        copy(object, objectEntity);
        return objectEntity;
    }

    @Override
    public Object map(Object2EditDTO fromObject, Object toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(Object2EditDTO objectDTO, Object objectEntity) {
        objectEntity.setName(objectDTO.getName());
        objectEntity.setFullname(objectDTO.getFullname());
        objectEntity.setOrdernumber(objectDTO.getOrdernumber());
        objectEntity.setGroup_of_objects(groupService.findGroupOfObjectById(objectDTO.getGroupOfObjectId()));
        objectEntity.setIsArchive(objectDTO.getIsArchive());
        objectEntity.setIsOnDelete(objectDTO.getIsOnDelete());

    }
}
