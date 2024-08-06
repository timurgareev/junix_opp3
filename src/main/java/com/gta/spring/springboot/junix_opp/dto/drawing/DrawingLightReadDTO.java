package com.gta.spring.springboot.junix_opp.dto.drawing;

import lombok.Value;

@Value
public class DrawingLightReadDTO {
    Long id;
    String code;
    Boolean isArchive;
}
