package com.gta.spring.springboot.junix_opp.dto.mark;

import com.gta.spring.springboot.junix_opp.dto.discipline.DisciplineReadDTO;
import com.gta.spring.springboot.junix_opp.dto.discipline.DisciplineReadMapper;
import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Mark;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MarkReadMapper implements Mapper<Mark, MarkReadDTO> {
    @Autowired
    private DisciplineReadMapper disciplineReadMapper ;

    @Override
    public MarkReadDTO map(Mark object) {
        DisciplineReadDTO disciplineReadDTO = Optional.ofNullable(object.getDiscipline())
                .map(disciplineReadMapper::map)
                .orElse(null);
        return new MarkReadDTO(
                object.getId(),
                object.getName(),
                object.getMarkkey(),
                disciplineReadDTO
        );
    }
}
