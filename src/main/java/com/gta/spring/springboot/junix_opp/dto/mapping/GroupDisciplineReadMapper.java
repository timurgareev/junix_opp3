package com.gta.spring.springboot.junix_opp.dto.mapping;


import com.gta.spring.springboot.junix_opp.dto.dto.DisciplineReadDTO;
import com.gta.spring.springboot.junix_opp.dto.dto.GroupDisciplinesReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Discipline;
import com.gta.spring.springboot.junix_opp.entity.GroupDiscipline;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupDisciplineReadMapper implements Mapper<GroupDiscipline, GroupDisciplinesReadDTO> {

    @Override
    public GroupDisciplinesReadDTO map(GroupDiscipline object) {
        return new GroupDisciplinesReadDTO(
                object.getId(),
                object.getName()
        );
    }

}
