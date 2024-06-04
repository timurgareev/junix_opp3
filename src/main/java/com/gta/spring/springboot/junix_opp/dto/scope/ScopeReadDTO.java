package com.gta.spring.springboot.junix_opp.dto.scope;

import com.gta.spring.springboot.junix_opp.entity.TypeOfWork;
import lombok.Value;

@Value
public class ScopeReadDTO {
        Long id;
        String name;
//        Long drawingId;
        String revision;
        String typeOfWork;
        String unitId;
        Double quantity;
        String comment;
//        User user;
//        String username;


}
