package com.gta.spring.springboot.junix_opp.dto.object;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Object;
import com.gta.spring.springboot.junix_opp.servise.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ObjectEditMapper implements Mapper<ObjectEditDTO, Object> {

    private final GroupService groupService;


    @Override
    public Object map(ObjectEditDTO object) {
        Object objectEntity = new Object();
        copy(object, objectEntity);
        return objectEntity;
    }

    @Override
    public Object map(ObjectEditDTO fromObject, Object toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ObjectEditDTO objectDTO, Object objectEntity) {
        objectEntity.setName(objectDTO.getName());
        objectEntity.setFullname(objectDTO.getFullname());
        objectEntity.setOrdernumber(objectDTO.getOrdernumber());
        objectEntity.setGroup_of_objects(groupService.findEntityById(objectDTO.getGroupOfObjectId()));
//        objectEntity.setIsArchive(objectDTO.getIsArchive());
//        objectEntity.setIsOnDelete(objectDTO.getIsOnDelete());

    }
}
