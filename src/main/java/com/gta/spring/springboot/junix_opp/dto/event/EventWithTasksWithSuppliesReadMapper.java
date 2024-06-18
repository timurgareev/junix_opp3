package com.gta.spring.springboot.junix_opp.dto.event;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadMapper;
import com.gta.spring.springboot.junix_opp.dto.task.TaskWithSuppliesReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskWithSuppliesReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventWithTasksWithSuppliesReadMapper implements Mapper<Event,EventWithTasksWithSuppliesReadDTO> {

    private final TaskWithSuppliesReadMapper taskWithSuppliesReadMapper;
    @Override
    public EventWithTasksWithSuppliesReadDTO map(Event object) {

        List<TaskWithSuppliesReadDTO> tasksDTOList = Optional.ofNullable(object.getTasks())
                .map(objects -> objects.stream()
                        .map(taskWithSuppliesReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        return new EventWithTasksWithSuppliesReadDTO(
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
