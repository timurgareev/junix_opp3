package com.gta.spring.springboot.junix_opp.dto.supplyRequest;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadDTO;
import com.gta.spring.springboot.junix_opp.dto.task.TaskReadMapper;
import com.gta.spring.springboot.junix_opp.entity.SupplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SupplyRequestWithTasksReadMapper implements Mapper<SupplyRequest, SupplyRequestWithTasksReadDTO> {

    private final TaskReadMapper taskReadMapper;
    @Override
    public SupplyRequestWithTasksReadDTO map(SupplyRequest object) {
        List<TaskReadDTO> tasksDTOList = Optional.ofNullable(object.getTasks())
                .map(objects -> objects.stream()
                        .map(taskReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return new SupplyRequestWithTasksReadDTO(
                object.getId(),
                object.getNumber(),
                object.getGroupOfSupply(),
                object.getDescription(),
                object.getComment(),
                object.getDrawing().getCode(),
                object.getRevision().getName(),
                object.getCreatedDate(),
                object.getUser().getUsername().toLowerCase(Locale.ROOT),
                tasksDTOList
        );
    }
}
