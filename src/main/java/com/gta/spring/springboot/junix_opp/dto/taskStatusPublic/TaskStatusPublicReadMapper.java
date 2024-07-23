package com.gta.spring.springboot.junix_opp.dto.taskStatusPublic;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.TaskStatusPublic;
import org.springframework.stereotype.Component;

@Component
public class TaskStatusPublicReadMapper implements Mapper<TaskStatusPublic, TaskStatusPublicReadDTO> {
    @Override
    public TaskStatusPublicReadDTO map(TaskStatusPublic object) {
        return new TaskStatusPublicReadDTO(
                object.getId(),
                object.getName()
        );
    }
}
