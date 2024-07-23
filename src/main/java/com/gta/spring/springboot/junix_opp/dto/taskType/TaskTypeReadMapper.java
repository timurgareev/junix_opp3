package com.gta.spring.springboot.junix_opp.dto.taskType;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.EventType;
import com.gta.spring.springboot.junix_opp.entity.TaskType;
import org.springframework.stereotype.Component;

@Component
public class TaskTypeReadMapper implements Mapper<TaskType, TaskTypeReadDTO> {
    @Override
    public TaskTypeReadDTO map(TaskType object) {
        return new TaskTypeReadDTO(
                object.getId(),
                object.getName()
        );
    }
}
