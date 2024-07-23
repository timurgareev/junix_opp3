package com.gta.spring.springboot.junix_opp.dto.eventType;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.EventType;
import com.gta.spring.springboot.junix_opp.entity.TaskStatusPublic;
import org.springframework.stereotype.Component;

@Component
public class EventTypeReadMapper implements Mapper<EventType, EventTypeReadDTO> {
    @Override
    public EventTypeReadDTO map(EventType object) {
        return new EventTypeReadDTO(
                object.getId(),
                object.getName()
        );
    }
}
