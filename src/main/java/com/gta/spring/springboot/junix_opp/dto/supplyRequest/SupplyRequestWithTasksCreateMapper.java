package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.task.TaskEditCreateMapper;
import com.gta.spring.springboot.junix_opp.entity.SupplyRequest;
import com.gta.spring.springboot.junix_opp.entity.Task;
import com.gta.spring.springboot.junix_opp.servise.DrawingService;
import com.gta.spring.springboot.junix_opp.servise.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SupplyRequestWithTasksCreateMapper implements Mapper<SupplyRequestWithTasksCreateDTO, SupplyRequest> {

    private final DrawingService drawingService;
    private final RevisionService revisionService;
    private final TaskEditCreateMapper taskEditCreateMapper;

    @Override
    public SupplyRequest map(SupplyRequestWithTasksCreateDTO object) {
        SupplyRequest supplyRequest = new SupplyRequest();
        copy(object, supplyRequest);
        return supplyRequest;
    }

    private void copy(SupplyRequestWithTasksCreateDTO object, SupplyRequest supplyRequest) {
        supplyRequest.setNumber(object.getNumber());
        supplyRequest.setGroupOfSupply(object.getGroupOfSupply());
        supplyRequest.setDescription(object.getDescriprion());
        supplyRequest.setComment(object.getComment());
        supplyRequest.setDrawing(drawingService.findDrawingById(object.getDrawingId()));
        supplyRequest.setRevision(revisionService.findRevisionById(object.getRevisionId()));

        if (object.getTaskDTOList() != null && !object.getTaskDTOList().isEmpty()) {
            List<Task> tasks = object.getTaskDTOList().stream()
                    .map(taskEditCreateMapper::map) // Используйте существующий маппер для задач
                    .collect(Collectors.toList());
            supplyRequest.setTasks(tasks);
        }
    }
}
