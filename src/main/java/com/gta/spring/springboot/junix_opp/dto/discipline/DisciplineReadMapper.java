package com.gta.spring.springboot.junix_opp.dto.discipline;


import com.gta.spring.springboot.junix_opp.dto.groupDiscipline.GroupDisciplinesReadDTO;
import com.gta.spring.springboot.junix_opp.dto.groupDiscipline.GroupDisciplineReadMapper;
import com.gta.spring.springboot.junix_opp.dto.Mapper;
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
