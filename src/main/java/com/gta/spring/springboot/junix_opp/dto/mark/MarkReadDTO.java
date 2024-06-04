package com.gta.spring.springboot.junix_opp.dto.mark;

import com.gta.spring.springboot.junix_opp.dto.discipline.DisciplineReadDTO;
import lombok.Value;

@Value
public class MarkReadDTO {
    Integer id;
    String name;
    String markKey;
    DisciplineReadDTO discipline;
}
