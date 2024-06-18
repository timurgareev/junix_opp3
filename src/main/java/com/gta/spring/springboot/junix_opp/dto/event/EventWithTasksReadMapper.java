package com.gta.spring.springboot.junix_opp.dto.event;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventWithTasksReadMapper implements Mapper<Event,EventWithTasksReadDTO> {

    private final TaskReadMapper taskReadMapper;
    @Override
    public EventWithTasksReadDTO map(Event object) {

        List<TaskReadDTO> tasksDTOList = Optional.ofNullable(object.getTasks())
                .map(objects -> objects.stream()
                        .map(taskReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return new EventWithTasksReadDTO(
                object.getId(),
                object.getName(),
                object.getDescription(),
                object.getEventType().getName(),
                object.getDrawing().getCode(),
                object.getRevision().getName(),
                object.getEventDate(),
                object.getIsSystemCreated(),
                tasksDTOList
        );
    }
}
