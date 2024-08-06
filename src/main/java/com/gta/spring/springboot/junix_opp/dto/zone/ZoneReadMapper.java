package com.gta.spring.springboot.junix_opp.dto.zone;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingLightReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZoneReadMapper implements Mapper<Zone, ZoneReadDTO> {

    @Autowired
    private DrawingLightReadMapper drawingReadMapper ;

    @Override
    public ZoneReadDTO map(Zone object) {

        return new ZoneReadDTO(
                object.getId(),
                object.getName(),
                object.getFullname(),
                object.getCode(),
                object.getOrdernumber(),
                object.getDeclaration(),
                object.getComment(),
                object.getProject().getId(),
                object.getProject().getName(),
                object.getIsArchive(),
                object.getIsOnDelete()


        );
    }

}
