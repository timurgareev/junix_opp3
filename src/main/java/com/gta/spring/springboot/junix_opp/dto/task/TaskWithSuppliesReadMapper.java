package com.gta.spring.springboot.junix_opp.dto.task;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestReadDTO;
import com.gta.spring.springboot.junix_opp.dto.supplyRequest.SupplyRequestReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskWithSuppliesReadMapper implements Mapper<Task, TaskWithSuppliesReadDTO> {

    private final SupplyRequestReadMapper supplyRequestReadMapper;
    @Override
    public TaskWithSuppliesReadDTO map(Task object) {
        List<SupplyRequestReadDTO> supplyDTOList = Optional.ofNullable(object.getSupplies())
                .map(objects -> objects.stream()
                        .map(supplyRequestReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return new TaskWithSuppliesReadDTO(
                object.getId(),
                object.getName(),
                object.getTaskType().getId(),
                object.getTaskType().getName(),
                object.getDrawing() !=null ? object.getDrawing().getId():null,
                object.getDrawing() !=null ? object.getDrawing().getCode():null,
                object.getRevision() !=null ? object.getRevision().getId():null,
                object.getRevision() !=null ? object.getRevision().getName():null,
                object.getProject().getId(),
                object.getProject().getName(),
                object.getDescription(),
                object.getIsQuestion(),
                object.getCreatedDate(),
                object.getCreatedUser().getId(),
                object.getCreatedUser().getUsername(),
                object.getResponsibleUser().getId(),
                object.getResponsibleUser().getUsername(),
                object.getDeadlineDate(),
                object.getIsComplete(),
                object.getCompleteDate(),
                object.getPriority().name(),
                object.getTaskStatusPrivate() != null ? object.getTaskStatusPrivate().getId():null,
                object.getTaskStatusPrivate() != null ? object.getTaskStatusPrivate().getName():null,
                object.getTaskStatusPublic() != null ? object.getTaskStatusPublic().getId():null,
                object.getTaskStatusPublic() != null ? object.getTaskStatusPublic().getName():null,
                object.getReport(),
                object.getLink1(),
                object.getLink2(),
                supplyDTOList
        );
    }
}
