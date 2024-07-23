package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.dto.eventType.EventTypeReadDTO;
import com.gta.spring.springboot.junix_opp.dto.taskType.TaskTypeReadDTO;
import com.gta.spring.springboot.junix_opp.dto.taskType.TaskTypeReadMapper;
import com.gta.spring.springboot.junix_opp.entity.TaskType;
import com.gta.spring.springboot.junix_opp.repository.TaskTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;
    private final TaskTypeReadMapper taskTypeReadMapper;

    public TaskType findTaskTypeById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(taskTypeRepository::findById)
                .orElse(null);
    }

    public List<TaskTypeReadDTO> findAll(){
        return taskTypeRepository.findAll().stream()
                .map(taskTypeReadMapper::map)
                .toList();
    }


}
