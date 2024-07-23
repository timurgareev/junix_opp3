package com.gta.spring.springboot.junix_opp.dto.taskStatusPrivate;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.typeOfWork.TypeOfWorkReadDTO;
import com.gta.spring.springboot.junix_opp.entity.TaskStatusPrivate;
import org.springframework.stereotype.Component;

@Component
public class TaskStatusPrivateReadMapper implements Mapper<TaskStatusPrivate, TaskStatusPrivateReadDTO> {
    @Override
    public TaskStatusPrivateReadDTO map(TaskStatusPrivate object) {
        return new TaskStatusPrivateReadDTO(
                object.getId(),
                object.getName()
        );
    }
}
