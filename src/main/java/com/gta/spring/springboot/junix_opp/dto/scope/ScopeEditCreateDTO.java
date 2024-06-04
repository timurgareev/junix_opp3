package com.gta.spring.springboot.junix_opp.dto.scope;

import lombok.Value;

@Value
public class ScopeEditCreateDTO {
        String name;
        Long drawingId;
        Long revisionId;
        Integer typeOfWorkId;
        Integer unitId;
        Double quantity;
        String comment;
//        String username;


}
