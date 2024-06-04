package com.gta.spring.springboot.junix_opp.dto.drawing;

import com.gta.spring.springboot.junix_opp.dto.mark.MarkReadDTO;
import lombok.Value;

@Value
public class DrawingMarkReadDTO {
    Integer id;
    String markDrawing;
    String markKey;
    MarkReadDTO markReadDTO;
}
