package com.gta.spring.springboot.junix_opp.dto.dto;

import lombok.Value;

import java.util.List;

@Value
public class ZoneReadDTO {
    Integer id;
    String name;
    String code;
    List<DrawingReadDTO> drawings;
}
