package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.event.*;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateMapper;
import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.entity.User;
import com.gta.spring.springboot.junix_opp.repository.EventRepository;
import com.gta.spring.springboot.junix_opp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final TaskRepository taskRepository;
    private final EventEditCreateMapper eventEditCreateMapper;
    private final EventWithNewTaskCreateMapper eventWithNewTaskCreateMapper;
    private final TaskEditCreateMapper taskEditCreateMapper;
    private final EventReadMapper eventReadMapper;
    private final UserService userService;

    @Transactional
    public Event createEvent(EventEditCreateDTO eventEditCreateDTO, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        Event event = eventEditCreateMapper.map(eventEditCreateDTO);
        event.setCreatedUser(user);
        return eventRepository.save(event);
    }

    public Optional<EventReadDTO> findById(Long id) {
        return eventRepository.findById(id)
                .map(eventReadMapper::map);
//                .map(event -> new EventReadDTO(event));
//                .orElseThrow(() -> new EventNotFoundException("Events with id=" + id + " not found"));
    }

    public List<EventReadDTO> findByDrawingId(Long id) {
        return eventRepository.findAllByDrawingId(id).stream()
                .map(eventReadMapper::map)
                .toList();
//                .map(event -> new EventReadDTO(event));
//                .orElseThrow(() -> new EventNotFoundException("Events with id=" + id + " not found"));
    }

    public Event createEventWithNewTasks(EventWithNewTaskCreateDTO eventWithNewTaskCreateDTO, Principal principal){
        User user = userService.getUserByPrincipal(principal);
        Event event = eventWithNewTaskCreateMapper.map(eventWithNewTaskCreateDTO);
        event.setCreatedUser(user);


//        for (TaskEditCreateDTO taskDTO:eventWithNewTaskCreateDTO.getTasks()){
//            Task task = taskEditCreateMapper.map(taskDTO);
//            task.setCreatedUser(user);
//            return taskRepository.save(task);
//
//            taskRepository.save(task)
//        }
        // Создание пустого множества для задач
        Set<Task> tasks = new HashSet<>();
        // Сохранение задач и добавление их в событие
        for (Task task: event.getTasks() ) {
            task.setCreatedUser(user);
            Task savedTask = taskRepository.save(task);
            tasks.add(savedTask);
        }
        // Установка сохраненных задач в событие
        event.setTasks(tasks);
        // Сохранение события с уже сохраненными задачами
        return  eventRepository.save(event);
    }

}
