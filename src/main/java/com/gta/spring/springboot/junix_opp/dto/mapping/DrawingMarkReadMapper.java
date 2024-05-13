package com.gta.spring.springboot.junix_opp.dto.mapping;

import com.gta.spring.springboot.junix_opp.dto.dto.DrawingMarkReadDTO;
import com.gta.spring.springboot.junix_opp.dto.dto.MarkReadDTO;
import com.gta.spring.springboot.junix_opp.entity.DrawingsMark;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DrawingMarkReadMapper implements Mapper<DrawingsMark, DrawingMarkReadDTO>  {
    @Autowired
    private MarkReadMapper markReadMapper ;

    @Override
    public DrawingMarkReadDTO map(DrawingsMark object) {
        MarkReadDTO markReadDTO = Optional.ofNullable(object.getMark())
                .map(markReadMapper::map)
                .orElse(null);
        return new DrawingMarkReadDTO(
                object.getId(),
                object.getMarkdrawing(),
                object.getMarkkey(),
                markReadDTO
        );
    }
}
