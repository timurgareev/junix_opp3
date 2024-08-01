package com.gta.spring.springboot.junix_opp.dto.groupOfObject;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectReadMapper;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectReadDTO;
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
public class GroupOfObjectReadMapper implements Mapper<GroupOfObject, GroupOfObjectReadDTO> {

    @Autowired
    private ObjectReadMapper objectReadMapper;

    @Override
    public GroupOfObjectReadDTO map(GroupOfObject groupOfobject) {
//        ObjectReadDTO object0 = Optional.ofNullable(object.getObjects())
//                .map(objectReadMapper::map)
//                .orElse(null); // 'это было не для листа
//        List<ObjectReadDTO> objectDTOlist = groupOfobject.getObjects().stream()
//                .map(objectReadMapper::map)
//                .collect(Collectors.toList()); //from gpt

        List<ObjectReadDTO> objectDTOlist = Optional.ofNullable(groupOfobject.getObjects())
                .map(objects -> objects.stream()
                        .map(objectReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
//        .orElse(Collections.emptyList())
//                .stream()
//                .map(objectReadMapper::map)
//                .collect(Collectors.toList());
        return new GroupOfObjectReadDTO(
                groupOfobject.getId(),
                groupOfobject.getName(),
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
