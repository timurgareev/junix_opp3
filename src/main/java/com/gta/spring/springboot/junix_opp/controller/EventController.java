package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.event.*;
import com.gta.spring.springboot.junix_opp.dto.scope.ScopeEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import com.gta.spring.springboot.junix_opp.servise.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventEditCreateDTO eventEditCreateDTO, Principal principal) {
        eventService.createEvent(eventEditCreateDTO, principal);
        return new ResponseEntity<>("Events successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<String> updateEvent(@PathVariable Long eventId,
                                              @RequestBody EventEditCreateDTO eventEditCreateDTO,
                                              @AuthenticationPrincipal Principal principal) {
        eventService.update(eventId, eventEditCreateDTO, principal);
        return ResponseEntity.ok("Event with id=" + eventId +" object successfully updated");
    } //добавить обработку ошибок на ненайденное айди

    @DeleteMapping("/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }//добавить обработку ошибок на ненайденное айди


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<EventReadDTO> eventOptional = eventService.findById(id);
        if (eventOptional.isPresent()) {
            EventReadDTO event = eventOptional.get();
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Событие с id=" + id + " не найдено");
        }
    }

    @GetMapping("/drawing/{id}")
    public List<EventReadDTO> findByDrawingId(@PathVariable("id") Long id) {
        return eventService.findByDrawingId(id);
    }


    @GetMapping("/{id}/withtasks/")
    public ResponseEntity<?> findByIdWithTasks(@PathVariable("id") Long id) {
        Optional<EventWithTasksReadDTO> eventOptional = eventService.findByIdWithTasks(id);
        if (eventOptional.isPresent()) {
            EventWithTasksReadDTO event = eventOptional.get();
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Event with id=" + id + " not found");
        }
    }
    @GetMapping("/drawing/{id}/withtasks")
    public List<EventWithTasksReadDTO> findByDrawingIdEventsWithTasks(@PathVariable("id") Long id) {
        return eventService.findByDrawingIdWithTasks(id);
    } //тут не надо обработки так как выгрузится пустой лист если не найдет

    @GetMapping("/{id}/withtaskswithsupplies/")
    public ResponseEntity<?> findByIdWithTasksWithSupplies(@PathVariable("id") Long id) {
        Optional<EventWithTasksWithSuppliesReadDTO> eventOptional = eventService.findByIdWithTasksWithSupplies(id);
        if (eventOptional.isPresent()) {
            EventWithTasksWithSuppliesReadDTO event = eventOptional.get();
            return ResponseEntity.ok(event);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Event with id=" + id + " not found");
        }
    }
    @GetMapping("/drawing/{id}/withtaskswithsupplies")
    public List<EventWithTasksWithSuppliesReadDTO> findByDrawingIdEventsWithTasksWithSupplies(@PathVariable("id") Long id) {
        return eventService.findByDrawingIdWithTasksWithSupplies(id);
    } //тут не надо обработки так как выгрузится пустой лист если не найдет

    @PostMapping("/{eventId}/task/{taskId}")
    public ResponseEntity<String> linkEventIdWithTaskId(@PathVariable Long eventId,
                                                        @PathVariable Long taskId) {
        eventService.linkEventIdWithTaskId(eventId, taskId);
        return ResponseEntity.ok("Succesfully add link event with id="+ eventId+" with task id="+taskId);
    }//добавить обработку ошибок на ненайденное айди

    @DeleteMapping("/{eventId}/task/{taskId}")
    public void removeTaskFromEvent(@PathVariable Long eventId, @PathVariable Long taskId) {
        eventService.removeTaskFromEvent(eventId, taskId);
    }//добавить обработку ошибок на ненайденное айди

    @PostMapping("/withtasks")
    public ResponseEntity<String> createEventWithTasks(@RequestBody EventWithNewTaskCreateDTO eventWithNewTaskCreateDTO , Principal principal) {
        eventService.createNewEventWithNewTasks(eventWithNewTaskCreateDTO, principal);
        return new ResponseEntity<>("Events with tasks successfully created", HttpStatus.CREATED);
    }
    @PostMapping("/task/{taskId}")
    public ResponseEntity<String> createNewEventWithTaskId(@PathVariable Long taskId,
                                                              @RequestBody EventEditCreateDTO eventDTOs,
                                                              Principal principal) {
        eventService.createNewEventWithTaskId(taskId, eventDTOs, principal);
        return new ResponseEntity<>("Event with existing task successfully created",HttpStatus.CREATED);
    }

}



//@GetMapping("/{userId}")
//public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") String userId) {
//    User user = userService.getUserById(Long.parseLong(userId));
//    UserDTO userDTO = userFacade.userToUserDTO(user);
//
//    return new ResponseEntity<>(userDTO, HttpStatus.OK);
//}


//изменил эти методы т.к. при не найденном айди выходила ошибка авторизации
//    @GetMapping("/{id}")
//    public EventReadDTO findById(@PathVariable("id") Long id) {
//        return eventService.findById(id)
////                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
//                .orElseThrow(() -> new EventNotFoundException("yt"));
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
//        try {
//            EventReadDTO event = eventService.findById(id)
//                    .orElseThrow(() -> new EventNotFoundException("Событие с id=" + id + " не найдено"));
//            return ResponseEntity.ok(event);
//        } catch (EventNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Событие с id=" + id + " не найдено");
//        }
//    }