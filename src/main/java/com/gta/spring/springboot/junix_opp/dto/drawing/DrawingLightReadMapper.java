package com.gta.spring.springboot.junix_opp.dto.drawing;


import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrawingLightReadMapper implements Mapper<Drawing, DrawingLightReadDTO> {

    @Override
    public DrawingLightReadDTO map(Drawing object) {

        return new DrawingLightReadDTO(
                object.getId(),
                object.getCode(),
                object.getIsArchive()
        );
    }

}
