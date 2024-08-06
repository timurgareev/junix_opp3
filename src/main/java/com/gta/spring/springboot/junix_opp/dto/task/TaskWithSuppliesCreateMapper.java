package com.gta.spring.springboot.junix_opp.dto.task;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestCreateMapper;
import com.gta.spring.springboot.junix_opp.entity.SupplyRequest;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.entity.enums.EPriority;
import com.gta.spring.springboot.junix_opp.servise.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskWithSuppliesCreateMapper implements Mapper<TaskWithSuppliesCreateDTO, Task> {

    private final SupplyRequestCreateMapper supplyRequestCreateMapper;
    private final DrawingService drawingService;
    private final RevisionService revisionService;
    private final TaskTypeService taskTypeService;
    private final ProjectService projectService;
    private final UserService userService;
    private final TaskStatusPrivateService taskStatusPrivateService;
    private final TaskStatusPublicService taskStatusPublicService;

    @Override
    public Task map(TaskWithSuppliesCreateDTO object) {
        Task task = new Task();
        copy(object, task);
        return task;
    }


    private void copy(TaskWithSuppliesCreateDTO object, Task task) {
        task.setName(object.getName());
        task.setTaskType(taskTypeService.findTaskTypeById(object.getTaskTypeId()));
        task.setDrawing(drawingService.findEntityById(object.getDrawingId()));
        task.setRevision(revisionService.findEntityById(object.getRevisionId()));
        task.setProject(projectService.findEntityById(object.getProjectId()));
        task.setDescription(object.getDescription());
        task.setResponsibleUser(userService.findUserById(object.getResponsibleUserId()));
        task.setDeadlineDate(object.getDeadlineDate());
        task.setPriority(EPriority.valueOf(object.getPriorityName()));
        task.setTaskStatusPrivate(taskStatusPrivateService.findTaskStatusPrivateById(object.getTaskStatusPrivate()));
        task.setTaskStatusPublic(taskStatusPublicService.findTaskStatusPublicById(object.getTaskStatusPublic()));

        if (object.getSupplyList() !=null && !object.getSupplyList().isEmpty()) {
            List<SupplyRequest> supplies = object.getSupplyList().stream()
                    .map(supplyRequestCreateMapper::map)
                    .collect(Collectors.toList());
            task.setSupplies(supplies);
        }
    }
}

