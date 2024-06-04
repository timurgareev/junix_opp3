package com.gta.spring.springboot.junix_opp.dto.groupOfObject;

import com.gta.spring.springboot.junix_opp.dto.object.ObjectReadDTO;
import lombok.Value;

import java.util.List;

@Value
public class GroupOfObjectReadDTO{
    Integer id;
    String name;
    List<ObjectReadDTO> objects;
}
