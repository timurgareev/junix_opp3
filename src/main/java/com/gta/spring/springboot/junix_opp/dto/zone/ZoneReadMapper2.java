package com.gta.spring.springboot.junix_opp.dto.zone;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadMapper;
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
public class ZoneReadMapper2 implements Mapper<Zone, ZoneReadDTO2> {

    @Autowired
    private DrawingReadMapper drawingReadMapper ;

    @Override
    public ZoneReadDTO2 map(Zone object) {

        return new ZoneReadDTO2(
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
