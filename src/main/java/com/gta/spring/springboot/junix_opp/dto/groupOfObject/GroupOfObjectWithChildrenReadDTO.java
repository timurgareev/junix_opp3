package com.gta.spring.springboot.junix_opp.dto.groupOfObject;

import com.gta.spring.springboot.junix_opp.dto.object.ObjectWithChildrenReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class GroupOfObjectWithChildrenReadDTO {
    Integer id;
    String name;
    Boolean isArchive;
    List<ObjectWithChildrenReadDTO> objects;
}
