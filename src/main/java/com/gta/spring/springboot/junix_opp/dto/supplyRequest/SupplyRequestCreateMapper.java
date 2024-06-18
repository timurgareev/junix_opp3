package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.SupplyRequest;
import com.gta.spring.springboot.junix_opp.servise.DrawingService;
import com.gta.spring.springboot.junix_opp.servise.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplyRequestCreateMapper implements Mapper<SupplyRequestCreateDTO, SupplyRequest> {

    private final DrawingService drawingService;
    private final RevisionService revisionService;

    @Override
    public SupplyRequest map(SupplyRequestCreateDTO object) {
        SupplyRequest supplyRequest = new SupplyRequest();
        copy(object, supplyRequest);
        return supplyRequest;
    }

    private void copy(SupplyRequestCreateDTO object, SupplyRequest supplyRequest) {
        supplyRequest.setNumber(object.getNumber());
        supplyRequest.setGroupOfSupply(object.getGroupOfSupply());
        supplyRequest.setDescription(object.getDescriprion());
        supplyRequest.setComment(object.getComment());
        supplyRequest.setDrawing(drawingService.findDrawingById(object.getDrawingId()));
        supplyRequest.setRevision(revisionService.findRevisionById(object.getRevisionId()));
    }
}


