package com.gta.spring.springboot.junix_opp.dto.dto;

import lombok.Value;

@Value
public class DrawingMarkReadDTO {
    Integer id;
    String markDrawing;
    String markKey;
    MarkReadDTO markReadDTO;
}
