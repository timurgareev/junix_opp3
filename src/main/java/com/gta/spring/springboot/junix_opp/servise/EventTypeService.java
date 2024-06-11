package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.entity.EventType;
import com.gta.spring.springboot.junix_opp.repository.EventTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventTypeService {

    private final EventTypeRepository eventTypeRepository;

    public EventType findById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(eventTypeRepository::findById)
                .orElse(null);
    }
}
