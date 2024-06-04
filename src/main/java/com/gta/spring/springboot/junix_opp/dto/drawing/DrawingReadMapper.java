package com.gta.spring.springboot.junix_opp.dto.drawing;


import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DrawingReadMapper implements Mapper<Drawing, DrawingReadDTO> {

    @Override
    public DrawingReadDTO map(Drawing object) {

        return new DrawingReadDTO(
                object.getId(),
                object.getCode()
        );
    }

}
