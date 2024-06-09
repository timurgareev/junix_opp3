package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.entity.TaskStatusPublic;
import com.gta.spring.springboot.junix_opp.entity.TaskType;
import com.gta.spring.springboot.junix_opp.repository.TaskStatusPublicRepository;
import com.gta.spring.springboot.junix_opp.repository.TaskTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskStatusPublicService {

    private final TaskStatusPublicRepository taskStatusPublicRepository;

    public TaskStatusPublic findTaskStatusPublicById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(taskStatusPublicRepository::findById)
                .orElse(null);
    }

}
