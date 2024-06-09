package com.gta.spring.springboot.junix_opp.dto.task;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskReadMapper implements Mapper<Task, TaskReadDTO> {


    @Override
    public TaskReadDTO map(Task object) {
        return new TaskReadDTO(
                object.getId(),
                object.getName(),
                object.getTaskType().getName(),
                object.getDrawing() !=null ? object.getDrawing().getCode():null,
                object.getRevision() !=null ? object.getRevision().getName():null,
                object.getProject().getId(),
                object.getDescription(),
                object.getCreatedDate(),
                object.getCreatedUser().getUsername(),
                object.getResponsibleUser().getUsername(),
                object.getDeadlineDate(),
                object.getPriority().name(),
                object.getTaskStatusPrivate() != null ? object.getTaskStatusPrivate().getName():null,
                object.getTaskStatusPublic() != null ? object.getTaskStatusPublic().getName():null
        );
    }
}
