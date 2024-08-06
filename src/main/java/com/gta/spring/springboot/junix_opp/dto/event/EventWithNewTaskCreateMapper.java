package com.gta.spring.springboot.junix_opp.dto.event;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateMapper;
import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.servise.DrawingService;
import com.gta.spring.springboot.junix_opp.servise.EventTypeService;
import com.gta.spring.springboot.junix_opp.servise.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventWithNewTaskCreateMapper implements Mapper<EventWithNewTaskCreateDTO, Event> {

    private final EventTypeService eventTypeService;
    private final DrawingService drawingService;
    private final RevisionService revisionService;
    private final TaskEditCreateMapper taskEditCreateMapper;


    @Override
    public Event map(EventWithNewTaskCreateDTO object) {
        Event event = new Event();
        copy(object, event);
        return event;
    }

    private void copy(EventWithNewTaskCreateDTO object, Event event) {
        event.setName(object.getName());
        event.setDescription(object.getDescription());
        event.setEventType(eventTypeService.findById(object.getEventTypeID()));
        event.setDrawing(drawingService.findEntityById(object.getDrawingId()));
        event.setRevision(revisionService.findEntityById(object.getRevisionId()));
        event.setEventDate(object.getEventDate());
        event.setIsSystemCreated(object.getIsSystemCreated());

        // Map tasks if available
        if (object.getTasks() != null && !object.getTasks().isEmpty()) {
            List<Task> tasks = object.getTasks().stream()
                    .map(taskEditCreateMapper::map) // Используйте существующий маппер для задач
                    .collect(Collectors.toList());
            event.setTasks(tasks);
        }
    }
}