package com.gta.spring.springboot.junix_opp.dto.event;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventReadMapper implements Mapper<Event,EventReadDTO> {

    @Override
    public EventReadDTO map(Event object) {
        return new EventReadDTO(
                object.getId(),
                object.getName(),
                object.getDescription(),
                object.getEventType().getName(),
                object.getDrawing().getCode(),
                object.getRevision().getName(),
                object.getEventDate(),
                object.getIsSystemCreated()
        );
    }
}
