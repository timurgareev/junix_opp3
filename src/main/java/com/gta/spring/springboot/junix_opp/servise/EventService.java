package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.event.EventEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.event.EventEditCreateMapper;
import com.gta.spring.springboot.junix_opp.dto.event.EventReadDTO;
import com.gta.spring.springboot.junix_opp.dto.event.EventReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.entity.User;
import com.gta.spring.springboot.junix_opp.repository.EventRepository;
import com.gta.spring.springboot.junix_opp.servise.helper.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventEditCreateMapper eventEditCreateMapper;
    private final EventReadMapper eventReadMapper;
    private final UserProvider userProvider;

    @Transactional
    public Event creareEvent(EventEditCreateDTO eventEditCreateDTO, Principal principal){
        User user = userProvider.getUserByPrincipal(principal);
        Event event = eventEditCreateMapper.map(eventEditCreateDTO);
        event.setCreatedUser(user);
        return eventRepository.save(event);
    }

    public Optional<EventReadDTO> findById(Long id) {
        return eventRepository.findById(id)
                .map(eventReadMapper::map);
    }
}
