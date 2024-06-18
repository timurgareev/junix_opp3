package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.SupplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SupplyRequestReadMapper implements Mapper<SupplyRequest, SupplyRequestReadDTO> {


    @Override
    public SupplyRequestReadDTO map(SupplyRequest object) {
        return new SupplyRequestReadDTO(
                object.getId(),
                object.getNumber(),
                object.getGroupOfSupply(),
                object.getDescription(),
                object.getComment(),
                object.getDrawing().getCode(),
                object.getRevision().getName(),
                object.getCreatedDate(),
                object.getUser().getUsername()
        );
    }
}

