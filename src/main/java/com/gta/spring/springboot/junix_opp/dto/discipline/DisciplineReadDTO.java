package com.gta.spring.springboot.junix_opp.dto.discipline;

import com.gta.spring.springboot.junix_opp.dto.groupDiscipline.GroupDisciplinesReadDTO;
import lombok.Value;

@Value
public class DisciplineReadDTO {
    Integer id;
    String name;
    GroupDisciplinesReadDTO groupDisciplinesReadDTO;
}
