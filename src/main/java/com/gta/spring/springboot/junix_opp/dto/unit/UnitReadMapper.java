package com.gta.spring.springboot.junix_opp.dto.unit;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Unit;
import org.springframework.stereotype.Component;

@Component
public class UnitReadMapper implements Mapper<Unit, UnitReadDTO>{
    @Override
    public UnitReadDTO map(Unit object) {
        return new UnitReadDTO(
                object.getId(),
                object.getName(),
                object.getCode()
        );
    }
}

