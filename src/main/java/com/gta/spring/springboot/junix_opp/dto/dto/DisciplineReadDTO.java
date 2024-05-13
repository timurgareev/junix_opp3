package com.gta.spring.springboot.junix_opp.dto.dto;

import lombok.Value;

@Value
public class DisciplineReadDTO {
    Integer id;
    String name;
    GroupDisciplinesReadDTO groupDisciplinesReadDTO;
}
