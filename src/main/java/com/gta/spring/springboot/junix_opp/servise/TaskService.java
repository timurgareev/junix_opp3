package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.task.*;
import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.entity.SupplyRequest;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.entity.User;
import com.gta.spring.springboot.junix_opp.repository.EventRepository;
import com.gta.spring.springboot.junix_opp.repository.SupplyRequestRepository;
import com.gta.spring.springboot.junix_opp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserService userService;
    private final TaskEditCreateMapper taskEditCreateMapper;
    private final TaskReadMapper taskReadMapper;
    private final TaskWithSuppliesReadMapper taskWithSuppliesReadMapper;
    private final TaskRepository taskRepository;
    private final SupplyRequestRepository supplyRepository;
    private final EventRepository eventRepository;

    @Transactional //1
    public Task createTask(TaskEditCreateDTO taskEditCreateDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Task task = taskEditCreateMapper.map(taskEditCreateDTO);
        task.setCreatedUser(user);
        return taskRepository.save(task);
    }


    @Transactional //2
    public void update(Long taskId, TaskEditCreateDTO taskEditCreateDTO, Principal principal) {
//        User user = userService.getUserByPrincipal(principal); добавить позже в EditUser;
        Task task = findTaskById(taskId);
        taskEditCreateMapper.map(taskEditCreateDTO, task);
        taskRepository.save(task);
    }

    @Transactional //3
    public void deleteTask(Long taskId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            for (SupplyRequest supply : task.getSupplies()) { //удалим связи с заявками
                supply.getTasks().remove(task);
                supplyRepository.save(supply);
            }
            for (Event event : task.getEvents()) { //удалим связи с событиями
                event.getTasks().remove(task);
                eventRepository.save(event);
            }
            taskRepository.deleteById(taskId);
        }
    }

    public Optional<TaskReadDTO> findById(Long id) {
        return taskRepository.findById(id)
                .map(taskReadMapper::map);
    }

    public Optional<TaskWithSuppliesReadDTO> findByIdWithSupplies(Long id) {
        return taskRepository.findById(id)
                .map(taskWithSuppliesReadMapper::map);
    }


    public Task findTaskById(Long taskId){
        return Optional.ofNullable(taskId)
                .flatMap(taskRepository::findById)
                .orElse(null);
    }

    public List<TaskReadDTO> findByDrawingId(Long id) {
        return taskRepository.findAllByDrawingId(id).stream()
                .map(taskReadMapper::map)
                .toList();
    }
    public List<TaskWithSuppliesReadDTO> findByDrawingIdWithSupplies(Long id) {
        return taskRepository.findAllByDrawingId(id).stream()
                .map(taskWithSuppliesReadMapper::map)
                .toList();
    }

    public List<TaskReadDTO> findByProjectId(Integer id) {
        return taskRepository.findAllByProjectsId(id).stream()
                .map(taskReadMapper::map)
                .toList();
    }
    public List<TaskWithSuppliesReadDTO> findByProjectIdWithSupplies(Integer id) {
        return taskRepository.findAllByProjectsId(id).stream()
                .map(taskWithSuppliesReadMapper::map)
                .toList();
    }
    @Transactional //8
    public void linkTaskIdWithSupplyId(Long taskId, Long supplyId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        Optional<SupplyRequest> supplyOpt = supplyRepository.findById(supplyId);
        if (supplyOpt.isPresent() && taskOpt.isPresent()) {
            Task task = taskOpt.get();
            SupplyRequest supply = supplyOpt.get();
            task.getSupplies().add(supply);
            taskRepository.save(task);
        }
    }

    @Transactional //9
    public void removeSupplyFromTask(Long taskId, Long supplyId) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        Optional<SupplyRequest> supplyOpt = supplyRepository.findById(supplyId);

        if (supplyOpt.isPresent() && taskOpt.isPresent()) {
            Task task = taskOpt.get();
            SupplyRequest supply = supplyOpt.get();
            task.getSupplies().remove(supply);
//            task.getEvents().remove(event);
            taskRepository.save(task);
//            taskRepository.save(task);
        }
    } //надо переписать лист на сет, т.к. удаление идет не оптимально, уадает все а потом вставлет


    @Transactional //13**
    public Task createNewTaskWithEventId(Long eventId, TaskEditCreateDTO taskEditCreateDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return eventRepository.findById(eventId).map(event -> {
            Task task = taskEditCreateMapper.map(taskEditCreateDTO);
            task.setCreatedUser(user);
            task.getEvents().add(event);
            return taskRepository.save(task);
        }).orElse(null); // или выбросить исключение
    }

    @Transactional //14**
    public Task createNewTaskWithSupplyId(Long supplyId, TaskEditCreateDTO taskEditCreateDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return supplyRepository.findById(supplyId).map(supply -> {
            Task task = taskEditCreateMapper.map(taskEditCreateDTO);
            task.setCreatedUser(user);
            task.getSupplies().add(supply);
            return taskRepository.save(task);
        }).orElse(null); // или выбросить исключение
    }


//    createNewTaskWithNewEvents - пока не делаем, т.к. думаю мне это не понадобится

    public List<TaskReadDTO> findAllByCurrentCreatedUser(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return taskRepository.findAllByCreatedUserId(user.getId()).stream()
                .map(taskReadMapper::map)
                .toList();
    }

    public List<TaskReadDTO> findAllByCurrentResponsibleUser(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        return taskRepository.findAllByResponsibleUserId(user.getId()).stream()
                .map(taskReadMapper::map)
                .toList();
    }

    public List<TaskReadDTO> findAllByCreatedUserId(Long id) {
        return taskRepository.findAllByCreatedUserId(id).stream()
                .map(taskReadMapper::map)
                .toList();
    }
    public List<TaskReadDTO> findAllByResponsibleUserId(Long id) {
        return taskRepository.findAllByResponsibleUserId(id).stream()
                .map(taskReadMapper::map)
                .toList();
    }




}
