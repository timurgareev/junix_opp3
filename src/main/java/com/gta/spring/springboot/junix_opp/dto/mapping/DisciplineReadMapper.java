package com.gta.spring.springboot.junix_opp.dto.mapping;


import com.gta.spring.springboot.junix_opp.dto.dto.DisciplineReadDTO;
import com.gta.spring.springboot.junix_opp.dto.dto.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.dto.GroupDisciplinesReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Discipline;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DisciplineReadMapper implements Mapper<Discipline, DisciplineReadDTO> {

    @Autowired
    private GroupDisciplineReadMapper groupDisciplineReadMapper;

    @Override
    public DisciplineReadDTO map(Discipline object) {

        GroupDisciplinesReadDTO groupDisciplinesReadDTO = Optional.ofNullable(object.getGroupDiscipline())
                .map(groupDisciplineReadMapper::map)
                .orElse(null);

        return new DisciplineReadDTO(
                object.getId(),
                object.getName(),
                groupDisciplinesReadDTO
        );
    }

}
