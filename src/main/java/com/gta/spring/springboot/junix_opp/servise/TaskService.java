package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateMapper;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.entity.User;
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
    private final TaskRepository taskRepository;


//    createTask
//    createTaskWithEvent
//    createTaskForProjectWithoutDrawing ? проверить может он примет null и не надо делать отедльные методы
//            create Task without revision

    @Transactional
    public Task createTask(TaskEditCreateDTO taskEditCreateDTO, Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        Task task = taskEditCreateMapper.map(taskEditCreateDTO);
        task.setCreatedUser(user);
        return taskRepository.save(task);
    }

    public Optional<TaskReadDTO> findById(Long id) {
        return taskRepository.findById(id)
                .map(taskReadMapper::map);
    }

    public List<TaskReadDTO> findByDrawingId(Long id) {
        return taskRepository.findAllByDrawingId(id).stream()
                .map(taskReadMapper::map)
                .toList();

    }

    public List<TaskReadDTO> findByProjectId(Integer id) {
        return taskRepository.findAllByProjectsId(id).stream()
                .map(taskReadMapper::map)
                .toList();

    }


}
