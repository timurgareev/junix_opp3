package com.gta.spring.springboot.junix_opp.dto.groupOfObject;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.GroupOfObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupOfObjectEditMapper implements Mapper<GroupOfObjectEditDTO, GroupOfObject> {

    @Override
    public GroupOfObject map(GroupOfObjectEditDTO object) {
        GroupOfObject groupOfobject = new GroupOfObject();
        copy(object,groupOfobject);
        return groupOfobject;
    }

    @Override
    public GroupOfObject map(GroupOfObjectEditDTO fromObject, GroupOfObject toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(GroupOfObjectEditDTO object, GroupOfObject groupOfobject) {
        groupOfobject.setName(object.getName());
        groupOfobject.setName(object.getName());
        groupOfobject.setFullname(object.getFullname());
        groupOfobject.setComment(object.getComment());
        groupOfobject.setOrdernumber(object.getOrdernumber());
        groupOfobject.setIsArchive(object.getIsArchive());
        groupOfobject.setIsOnDelete(object.getIsOnDelete());

    }


}


