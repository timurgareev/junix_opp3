package com.gta.spring.springboot.junix_opp.dto.groupOfObject;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectWithChildrenReadMapper;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.entity.GroupOfObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupOfObjectWithChildrenReadMapper implements Mapper<GroupOfObject, GroupOfObjectWithChildrenReadDTO> {

    @Autowired
    private ObjectWithChildrenReadMapper objectReadMapper;

    @Override
    public GroupOfObjectWithChildrenReadDTO map(GroupOfObject groupOfobject) {

        List<ObjectWithChildrenReadDTO> objectDTOlist = Optional.ofNullable(groupOfobject.getObjects())
                .map(objects -> objects.stream()
                        .map(objectReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return new GroupOfObjectWithChildrenReadDTO(
                groupOfobject.getId(),
                groupOfobject.getName(),
                groupOfobject.getIsArchive(),
                objectDTOlist
        );

    }



}
//
//@Component
//@RequiredArgsConstructor
//public class UserReadMapper implements Mapper<User, UserReadDto> {
//
//    private final CompanyReadMapper companyReadMapper;
//
//    @Override
//    public UserReadDto map(User object) {
//        CompanyReadDto company = Optional.ofNullable(object.getCompany())
//                .map(companyReadMapper::map)
//                .orElse(null);
//        return new UserReadDto(
//                object.getId(),
//                object.getUsername(),
//                object.getBirthDate(),
//                object.getFirstname(),
//                object.getLastname(),
//                object.getRole(),
//                company
//        );
//    }
//}
