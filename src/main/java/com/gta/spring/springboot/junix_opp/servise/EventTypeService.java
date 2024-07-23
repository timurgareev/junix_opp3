package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.eventType.EventTypeReadDTO;
import com.gta.spring.springboot.junix_opp.dto.eventType.EventTypeReadMapper;
import com.gta.spring.springboot.junix_opp.dto.unit.UnitReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.entity.EventType;
import com.gta.spring.springboot.junix_opp.repository.EventTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventTypeService {

    private final EventTypeRepository eventTypeRepository;
    private final EventTypeReadMapper eventTypeReadMapper;

    public EventType findById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(eventTypeRepository::findById)
                .orElse(null);
    }
    public List<EventTypeReadDTO> findAll(){
        return eventTypeRepository.findAll().stream()
                .map(eventTypeReadMapper::map)
                .toList();
    }
}
