package com.gta.spring.springboot.junix_opp.dto.zone;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadMapper;
import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ZoneReadMapper implements Mapper<Zone, ZoneReadDTO> {

    @Autowired
    private DrawingReadMapper drawingReadMapper ;

    @Override
    public ZoneReadDTO map(Zone object) {

        List<DrawingReadDTO> drawingsDTOList = Optional.ofNullable(object.getDrawings())
                .map(objects -> objects.stream()
                        .map(drawingReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return new ZoneReadDTO(
                object.getId(),
                object.getName(),
                object.getCode(),
                drawingsDTOList

        );
    }

}
