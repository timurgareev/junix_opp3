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
                object.getProject() !=null ? object.getProject().getId():null,
                object.getProject() !=null ? object.getProject().getName():null,
                object.getDrawing() !=null ? object.getDrawing().getId():null,
                object.getDrawing() !=null ? object.getDrawing().getCode():null,
                object.getRevision() !=null ? object.getRevision().getId():null,
                object.getRevision() !=null ? object.getRevision().getName():null,
                object.getCreatedDate(),
                object.getUser() !=null ? object.getUser().getId():null,
                object.getUser() !=null ? object.getUser().getUsername():null,
                object.getSupplyRequestDate(),
                object.getYear(),
                object.getComment_1c(),
                object.getCondition(),
                object.getDrawing_1c(),
                object.getRegDate(),
                object.getReg_number(),
                object.getStatus(),
                object.getStatus_sign()
        );
    }
}

