package com.gta.spring.springboot.junix_opp.dto.groupOfObject;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.GroupOfObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupOfObjectReadMapper implements Mapper<GroupOfObject, GroupOfObjectReadDTO> {

    @Override
    public GroupOfObjectReadDTO map(GroupOfObject groupOfobject) {



        return new GroupOfObjectReadDTO(
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

