package com.gta.spring.springboot.junix_opp.dto.zone;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class ZoneReadDTO2 {
    Integer id;
    String name;
    String fullName;
    String code;
    int ordernumber;
    String declaration;
    String comment;
    Integer projectId;
    String projectName;
    Boolean isArchive;
    Boolean isOnDelete;

}
