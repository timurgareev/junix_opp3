package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.dto.event.EventEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.event.EventReadDTO;
import com.gta.spring.springboot.junix_opp.servise.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    public final EventService eventService;

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




    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody EventEditCreateDTO eventEditCreateDTO, Principal principal) {
        eventService.createEvent(eventEditCreateDTO, principal);
        return new ResponseEntity<>("Events successfully created", HttpStatus.CREATED);
    }

    @GetMapping("/drawing/{id}")
    public List<EventReadDTO> findByDrawingId(@PathVariable("id") Long id) {
        return eventService.findByDrawingId(id);
    }
}

//@GetMapping("/{userId}")
//public ResponseEntity<UserDTO> getUserProfile(@PathVariable("userId") String userId) {
//    User user = userService.getUserById(Long.parseLong(userId));
//    UserDTO userDTO = userFacade.userToUserDTO(user);
//
//    return new ResponseEntity<>(userDTO, HttpStatus.OK);
//}