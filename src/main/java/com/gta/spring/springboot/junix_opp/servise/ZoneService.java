package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.entity.DrawingsMark;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import com.gta.spring.springboot.junix_opp.repository.DrawingMarkRepository;
import com.gta.spring.springboot.junix_opp.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZoneService {

private final ZoneRepository zoneRepository;
    public Zone findZoneById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(zoneRepository::findById)
                .orElse(null);
    }


}
