package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.unit.UnitReadDTO;
import com.gta.spring.springboot.junix_opp.dto.unit.UnitReadMapper;
import com.gta.spring.springboot.junix_opp.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;
    private final UnitReadMapper unitReadMapper;

    public List<UnitReadDTO> findAll(){
        return unitRepository.findAll().stream()
                .map(unitReadMapper::map)
                .toList();
    }
}
