package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.entity.TaskType;
import com.gta.spring.springboot.junix_opp.repository.TaskTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;

    public TaskType findTaskTypeById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(taskTypeRepository::findById)
                .orElse(null);
    }



}
