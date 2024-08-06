package com.gta.spring.springboot.junix_opp.dto.zone;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingLightReadDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingLightReadMapper;
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
public class ZoneWithChildrenReadMapper implements Mapper<Zone, ZoneWithChildrenReadDTO> {

    @Autowired
    private DrawingLightReadMapper drawingReadMapper ;

    @Override
    public ZoneWithChildrenReadDTO map(Zone object) {

        List<DrawingLightReadDTO> drawingsDTOList = Optional.ofNullable(object.getDrawings())
                .map(objects -> objects.stream()
                        .map(drawingReadMapper::map)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
        return new ZoneWithChildrenReadDTO(
                object.getId(),
                object.getName(),
                object.getCode(),
                object.getIsArchive(),
                drawingsDTOList

        );
    }

}
