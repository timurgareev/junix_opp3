package com.gta.spring.springboot.junix_opp.dto.task;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Revision;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.entity.enums.EPriority;
import com.gta.spring.springboot.junix_opp.servise.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskEditCreateMapper implements Mapper<TaskEditCreateDTO, Task> {

    private final DrawingService drawingService;
    private final TaskTypeService taskTypeService;
    private final RevisionService revisionService;
    private final ProjectService projectService;
    private final UserService userService;
    private final TaskStatusPrivateService taskStatusPrivateService;
    private final TaskStatusPublicService taskStatusPublicService;

    @Override
    public Task map(TaskEditCreateDTO fromObject, Task toObject) {
        copy(fromObject,toObject);
        return toObject;
    }

    @Override
    public Task map(TaskEditCreateDTO object) {
        Task task = new Task();
        copy(object,task);
        return task;
    }

    private void copy(TaskEditCreateDTO object, Task task) {
        task.setName(object.getName());
        task.setTaskType(taskTypeService.findTaskTypeById(object.getTaskTypeId()));
        task.setDrawing(drawingService.findDrawingById(object.getDrawingId()));
        task.setRevision(revisionService.findRevisionById(object.getRevisionId()));
        task.setProject(projectService.findProjectById(object.getProjectId()));
        task.setDescription(object.getDescription());
        task.setIsQuestion(object.getIsQuestion());
        task.setResponsibleUser(userService.findUserById(object.getResponsibleUserId()));
        task.setDeadlineDate(object.getDeadlineDate());
        task.setIsComplete(object.getIsComplete());
        task.setCompleteDate(object.getCompleteDate());
        task.setPriority(EPriority.valueOf(object.getPriorityName()));
        task.setTaskStatusPrivate(taskStatusPrivateService.findTaskStatusPrivateById(object.getTaskStatusPrivate()));
        task.setTaskStatusPublic(taskStatusPublicService.findTaskStatusPublicById(object.getTaskStatusPublic()));
        task.setReport(object.getReport());
        task.setLink1(object.getLink1());
        task.setLink2(object.getLink2());
    }
}

