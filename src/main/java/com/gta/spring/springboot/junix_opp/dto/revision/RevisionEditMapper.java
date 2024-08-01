package com.gta.spring.springboot.junix_opp.dto.revision;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.event.EventEditCreateDTO;
import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.entity.Revision;
import com.gta.spring.springboot.junix_opp.servise.DrawingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RevisionEditMapper implements Mapper<RevisionEditDTO, Revision> {

    private final DrawingService drawingService;
    @Override
    public Revision map(RevisionEditDTO object) {
        Revision revision = new Revision();
        copy(object,revision);
        return revision;
    }

    @Override
    public Revision map(RevisionEditDTO fromObject, Revision toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    private void copy(RevisionEditDTO object, Revision revision){


        revision.setDrawing(drawingService.findDrawingById(object.getDrawingId()));
        revision.setName(object.getName());
        revision.setStatus(object.getStatus());
        revision.setDateInbox(object.getDataInbox());
        revision.setDateOutbox(object.getDateOutbox());
        revision.setInproductionDateSystem(object.getInproducrionDateSystem());
        revision.setInproductionDateManual(object.getInproducrionDateManual());
        revision.setRateNumber(object.getRateNumber());
        revision.setIsLatest(object.getIsLatest());
        revision.setComment1(object.getComment1());
        revision.setComment2(object.getComment2());
        revision.setIsArchive(object.getIsArchive());
        revision.setIsOnDelete(object.getIsOnDelete());



    }
}
