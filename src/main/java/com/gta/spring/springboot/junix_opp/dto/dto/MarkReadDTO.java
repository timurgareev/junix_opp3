package com.gta.spring.springboot.junix_opp.dto.dto;

import lombok.Value;

@Value
public class MarkReadDTO {
    Integer id;
    String name;
    String markKey;
    DisciplineReadDTO discipline;
}
