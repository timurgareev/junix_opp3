package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.entity.TaskStatusPrivate;
import com.gta.spring.springboot.junix_opp.repository.TaskStatusPrivateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskStatusPrivateService {

    private final TaskStatusPrivateRepository taskStatusPrivateRepository;

    public TaskStatusPrivate findTaskStatusPrivateById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(taskStatusPrivateRepository::findById)
                .orElse(null);
    }

}
