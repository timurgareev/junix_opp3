package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.event.*;
import com.gta.spring.springboot.junix_opp.dto.scope.ScopeEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateMapper;
import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.entity.Scope;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.entity.User;
import com.gta.spring.springboot.junix_opp.repository.EventRepository;
import com.gta.spring.springboot.junix_opp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final TaskRepository taskRepository;
    private final EventEditCreateMapper eventEditCreateMapper;
    private final EventWithNewTaskCreateMapper eventWithNewTaskCreateMapper;
    private final EventReadMapper eventReadMapper;
    private final UserService userService;
    private final EventWithTasksReadMapper eventWithTasksReadMapper;
    private final TaskEditCreateMapper taskEditCreateMapper;
    private final TaskService taskService;
    private EventWithTasksWithSuppliesReadMapper eventWithTasksWithSuppliesReadMapper;


    public Optional<EventReadDTO> findById(Long id) {
        return eventRepository.findById(id)
                .map(eventReadMapper::map);
//                .map(event -> new EventReadDTO(event));
//                .orElseThrow(() -> new EventNotFoundException("Events with id=" + id + " not found"));
    }

    public Optional<EventWithTasksReadDTO> findByIdWithTasks(Long id) {
        return eventRepository.findById(id)
                .map(eventWithTasksReadMapper::map);
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


    public List<EventWithTasksReadDTO> findByDrawingIdWithTasks(Long id) {
        List<Event> events = eventRepository.findAllByDrawingIdWithTasks(id);
        return events.stream()
                .map(eventWithTasksReadMapper::map)
                .toList();
    }

    public Optional<EventWithTasksWithSuppliesReadDTO> findByIdWithTasksWithSupplies(Long id) {
        return eventRepository.findById(id)
                .map(eventWithTasksWithSuppliesReadMapper::map);
    }

    public List<EventWithTasksWithSuppliesReadDTO> findByDrawingIdWithTasksWithSupplies(Long id) {
        List<Event> events = eventRepository.findAllByDrawingIdWithTasks(id);
        return events.stream()
                .map(eventWithTasksWithSuppliesReadMapper::map)
                .toList();
    }


    @Transactional
    public Event createEvent(EventEditCreateDTO eventEditCreateDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Event event = eventEditCreateMapper.map(eventEditCreateDTO);
        event.setCreatedUser(user);
        return eventRepository.save(event);
    }

    @Transactional //12*
    public Event createNewEventWithNewTasks(EventWithNewTaskCreateDTO eventWithNewTaskCreateDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Event event = eventWithNewTaskCreateMapper.map(eventWithNewTaskCreateDTO);
        event.setCreatedUser(user);

        // Создание пустого множества для задач
        List<Task> tasks = new ArrayList<>();
        // Сохранение задач и добавление их в событие
        for (Task task : event.getTasks()) {
            task.setCreatedUser(user);
            Task savedTask = taskRepository.save(task);
            tasks.add(savedTask);
        }
        // Установка сохраненных задач в событие
        event.setTasks(tasks);
        // Сохранение события с уже сохраненными задачами
        return eventRepository.save(event);
    }//  надо добработать - обработать ошибку при создании дублей - сейчас ошибка авторизации


    @Transactional //13**
    public Event createNewEventWithTaskId(Long taskId, EventEditCreateDTO eventEditCreateDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return taskRepository.findById(taskId).map(task -> {
            Event event = eventEditCreateMapper.map(eventEditCreateDTO);
            event.setCreatedUser(user);
            event.getTasks().add(task);
//            task.getEvents().add(event); это дублирует создание в таблице event_task второй записи
//            taskRepository.save(task); // Сохраняем task, чтобы обновить связь
            return eventRepository.save(event);
        }).orElse(null); // или выбросить исключение
    }


    @Transactional //3
    public void deleteEvent(Long eventId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            for (Task task : event.getTasks()) {
                task.getEvents().remove(event);
                taskRepository.save(task);
            }
            eventRepository.deleteById(eventId);
        }
    }

    @Transactional //11
    public void removeTaskFromEvent(Long eventId, Long taskId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (eventOpt.isPresent() && taskOpt.isPresent()) {
            Event event = eventOpt.get();
            Task task = taskOpt.get();
            event.getTasks().remove(task);
            task.getEvents().remove(event);
            eventRepository.save(event);
            taskRepository.save(task);
        }
    } //надо переписать лист на сет, т.к. удаление идет не оптимально, уадает все а потом вставлет

    @Transactional //10
    public void linkEventIdWithTaskId(Long eventId, Long taskId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (eventOpt.isPresent() && taskOpt.isPresent()) {
            Event event = eventOpt.get();
            Task task = taskOpt.get();
            event.getTasks().add(task);
//            taskRepository.save(task);
            eventRepository.save(event);
//            task.getEvents().add(event);
        }
    }

    @Transactional //2
    public void update(Long eventId, EventEditCreateDTO eventEditCreateDTO, Principal principal) {
//        User user = userService.getUserByPrincipal(principal); добавить позже в EditUser;
        Event event = findEventById(eventId);
        eventEditCreateMapper.map(eventEditCreateDTO, event);
        eventRepository.save(event);
    }

    public Event findEventById(Long eventId){
        return Optional.ofNullable(eventId)
                .flatMap(eventRepository::findById)
                .orElse(null);
    }




}
//    @Transactional //10
//    public EventDTO linkEventWithTasks(Long eventId, List<Long> taskIds) {
//        Optional<Event> eventOpt = eventRepository.findById(eventId);
//        if (eventOpt.isPresent()) {
//            Event event = eventOpt.get();
//            Set<Task> tasks = taskIds.stream()
//                    .map(taskId -> taskRepository.findById(taskId))
//                    .filter(Optional::isPresent)
//                    .map(Optional::get)
//                    .collect(Collectors.toSet());
//            event.getTasks().addAll(tasks);
//            for (Task task : tasks) {
//                task.getEvents().add(event);
//                taskRepository.save(task);
//            }
//            Event savedEvent = eventRepository.save(event);
//            return eventMapper.toDTO(savedEvent);
//        }
//        return null; // or throw exception
//    }



//    update



//Черновики:
//   @Transactional это я передумал делать так как очень путтано
//    public Event createEventWithNewTasks(Long eventId, List<TaskEditCreateDTO> taskDTOs, Principal principal) {
//       User user = userService.getUserByPrincipal(principal);
//       Optional<Event> eventOpt = eventRepository.findById(eventId);
//       if (eventOpt.isPresent()) {
//           Event event = eventOpt.get();
//           List<Task> tasks = taskDTOs.stream()
//                   .map(taskEditCreateMapper::map)
//                   .toList();
//           event.getTasks().addAll(tasks);
//           for (Task task : tasks) {
//               task.setCreatedUser(user);
//               task.getEvents().add(event);
//               taskRepository.save(task);
//           }
//
//           return eventRepository.save(event);
//       }
//       return null; // or throw exception
//   }


//    public List<EventWithTasksReadDTO> findEventsWithTasksByDrawingId(Long id) {
//        return eventRepository.findAllByDrawingId(id).stream()
//                .map(eventWithTasksReadMapper::map)
//                .toList();
//    }