package com.gta.spring.springboot.junix_opp.dto.project;

import com.gta.spring.springboot.junix_opp.dto.zone.ZoneReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class ProjectReadDTO2 {
    Integer id;
    String name;
    String fullname;
    String code;
    Integer objectId;
    String objectName;
    Boolean isArchive;
    Boolean isOnDelete;


}