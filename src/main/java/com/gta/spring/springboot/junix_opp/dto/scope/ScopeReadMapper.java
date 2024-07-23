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
        Double quantity = object.getQuantity();
        double quantityValue = (quantity != null && !Double.isNaN(quantity)) ? quantity : 0;

        return new ScopeReadDTO(
                object.getId(),
                object.getName(),
                object.getRevision() !=null ? object.getRevision().getName() : null,
                object.getTypeOfWork() !=null ? object.getTypeOfWork().getName() : null,
                object.getUnit() !=null ? object.getUnit().getCode() : null,
//                !Double.isNaN(object.getQuantity()) ? object.getQuantity() : 0,
                quantityValue,
                object.getComment()
//                object.getUser().getUsername()


        );
    }
}


