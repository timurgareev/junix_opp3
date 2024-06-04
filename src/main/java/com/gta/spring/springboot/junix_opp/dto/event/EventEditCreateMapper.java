package com.gta.spring.springboot.junix_opp.dto.event;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.entity.EventType;
import com.gta.spring.springboot.junix_opp.entity.Revision;
import com.gta.spring.springboot.junix_opp.repository.DrawingRepository;
import com.gta.spring.springboot.junix_opp.repository.EventTypeRepository;
import com.gta.spring.springboot.junix_opp.repository.RevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class EventEditCreateMapper implements Mapper<EventEditCreateDTO, Event> {

    private final EventTypeRepository eventTypeRepository;
    private final DrawingRepository drawingRepository;
    private final RevisionRepository revisionRepository;


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
        event.setEventType(getEventType(object.getEventTypeID()));
        event.setDrawing(getDrawing(object.getDrawingId()));
        event.setRevision(getRevision(object.getRevisionId()));
        event.setEventDate(object.getEventDate());
        event.setIsSystemCreated(object.getIsSystemCreated());
    }

    public EventType getEventType(Integer eventTypeId) {
        return Optional.ofNullable(eventTypeId)
                .flatMap(eventTypeRepository::findById)
                .orElse(null);
    }
    public Drawing getDrawing(Long drawingId){
        return Optional.ofNullable(drawingId)
                .flatMap(drawingRepository::findById)
                .orElse(null);
    }

    public Revision getRevision(Long revisionId){
        return Optional.ofNullable(revisionId)
                .flatMap(revisionRepository::findById)
                .orElse(null);
    }

}
