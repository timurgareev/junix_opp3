package com.gta.spring.springboot.junix_opp.dto.event;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.repository.DrawingRepository;
import com.gta.spring.springboot.junix_opp.repository.EventTypeRepository;
import com.gta.spring.springboot.junix_opp.repository.RevisionRepository;
import com.gta.spring.springboot.junix_opp.servise.DrawingService;
import com.gta.spring.springboot.junix_opp.servise.EventTypeService;
import com.gta.spring.springboot.junix_opp.servise.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventEditCreateMapper implements Mapper<EventEditCreateDTO, Event> {

    private final EventTypeRepository eventTypeRepository;
    private final DrawingRepository drawingRepository;
    private final RevisionRepository revisionRepository;
    private final EventTypeService eventTypeService;
    private final DrawingService drawingService;
    private final RevisionService revisionService;

    @Override
    public Event map(EventEditCreateDTO fromObject, Event toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    @Override
    public Event map(EventEditCreateDTO object) {
        Event event = new Event();
        copy(object,event);
        return event;
    }

    private void copy(EventEditCreateDTO object, Event event){
        event.setName(object.getName());
        event.setDescription(object.getDescription());
        event.setEventType(eventTypeService.findById(object.getEventTypeID()));
        event.setDrawing(drawingService.findEntityById(object.getDrawingId()));
        event.setRevision(revisionService.findEntityById(object.getRevisionId()));
        event.setEventDate(object.getEventDate());
        event.setIsSystemCreated(object.getIsSystemCreated());
    }
}
