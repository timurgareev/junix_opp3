package com.gta.spring.springboot.junix_opp.dto.zone;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class ZoneReadDTO {
    Integer id;
    String name;
    String code;
    List<DrawingReadDTO> drawings;
}
