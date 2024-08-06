package com.gta.spring.springboot.junix_opp.dto.zone;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingLightReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class ZoneWithChildrenReadDTO {
    Integer id;
    String name;
    String code;
    Boolean isArchive;
    List<DrawingLightReadDTO> drawings;
}
