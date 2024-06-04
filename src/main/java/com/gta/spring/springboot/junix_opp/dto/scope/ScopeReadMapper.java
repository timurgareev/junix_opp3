package com.gta.spring.springboot.junix_opp.dto.scope;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Scope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScopeReadMapper implements Mapper<Scope, ScopeReadDTO> {

    @Override
    public ScopeReadDTO map(Scope object) {
        return new ScopeReadDTO(
                object.getId(),
                object.getName(),
                object.getRevision().getName(),
                object.getTypeOfWork().getName(),
                object.getUnit().getCode(),
                object.getQuantity(),
                object.getComment()
//                object.getUser().getUsername()
        );
    }
}