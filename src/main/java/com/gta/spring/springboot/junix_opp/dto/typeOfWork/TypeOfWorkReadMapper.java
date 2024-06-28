package com.gta.spring.springboot.junix_opp.dto.typeOfWork;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.TypeOfWork;
import org.springframework.stereotype.Component;

@Component
public class TypeOfWorkReadMapper implements Mapper<TypeOfWork, TypeOfWorkReadDTO> {
    @Override
    public TypeOfWorkReadDTO map(TypeOfWork object) {
        return new TypeOfWorkReadDTO(
                object.getId(),
                object.getName(),
                object.getFullname(),
                object.getComment()
        );
    }
}
