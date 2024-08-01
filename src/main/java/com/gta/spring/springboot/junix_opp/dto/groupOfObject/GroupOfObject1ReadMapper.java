package com.gta.spring.springboot.junix_opp.dto.groupOfObject;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.GroupOfObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupOfObject1ReadMapper implements Mapper<GroupOfObject, GroupOfObject1ReadDTO> {

    @Override
    public GroupOfObject1ReadDTO map(GroupOfObject groupOfobject) {



        return new GroupOfObject1ReadDTO(
                groupOfobject.getId(),
                groupOfobject.getName(),
                groupOfobject.getFullname(),
                groupOfobject.getComment(),
                groupOfobject.getOrdernumber(),
                groupOfobject.getIsArchive(),
                groupOfobject.getIsOnDelete()
        );
    }
}

