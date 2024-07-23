package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.dto.eventType.EventTypeReadDTO;
import com.gta.spring.springboot.junix_opp.dto.taskStatusPrivate.TaskStatusPrivateReadDTO;
import com.gta.spring.springboot.junix_opp.dto.taskStatusPrivate.TaskStatusPrivateReadMapper;
import com.gta.spring.springboot.junix_opp.entity.TaskStatusPrivate;
import com.gta.spring.springboot.junix_opp.repository.TaskStatusPrivateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskStatusPrivateService {

    private final TaskStatusPrivateRepository taskStatusPrivateRepository;
    private final TaskStatusPrivateReadMapper taskStatusPrivateReadMapper;

    public TaskStatusPrivate findTaskStatusPrivateById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(taskStatusPrivateRepository::findById)
                .orElse(null);
    }

    public List<TaskStatusPrivateReadDTO> findAll(){
        return taskStatusPrivateRepository.findAll().stream()
                .map(taskStatusPrivateReadMapper::map)
                .toList();
    }

}
