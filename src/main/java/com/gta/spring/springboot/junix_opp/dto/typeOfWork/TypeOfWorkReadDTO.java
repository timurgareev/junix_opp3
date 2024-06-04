package com.gta.spring.springboot.junix_opp.dto.typeOfWork;

import lombok.Value;

@Value
public class TypeOfWorkReadDTO {
    Integer id;
    String name;
    String fullname;
    String comment;
}
