package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.zone.ZoneEditDTO;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneEditMapper;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneReadDTO;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Project;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import com.gta.spring.springboot.junix_opp.exceptions.EntityAlreadyExistsException;
import com.gta.spring.springboot.junix_opp.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZoneService implements BaseCRUDService<Zone,
        ZoneReadDTO, ZoneEditDTO, Integer>{

private final ZoneRepository zoneRepository;
private final ZoneReadMapper readMapper;
private final ZoneEditMapper editMapper;

    @Override
    public Zone findEntityById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(zoneRepository::findById)
                .orElse(null);
    }

    @Override
    public List<ZoneReadDTO> findAll() {
        return zoneRepository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    @Override
    public Optional<ZoneReadDTO> findById(Integer id) {
        return zoneRepository.findById(id)
                .map(readMapper::map);
    }


    @Override
    @Transactional
    public void create(ZoneEditDTO editDTO, Principal principal) {
        if (zoneRepository.existsByCodeAndProjectId(editDTO.getCode(),editDTO.getProjectId())) {
            throw new EntityAlreadyExistsException("Zone with code '" + editDTO.getCode() + "' already exists in this project.");
        }
        Zone zone = editMapper.map(editDTO);
        zoneRepository.save(zone);
    }

    @Override
    @Transactional
    public void update(Integer id, ZoneEditDTO editDTO, Principal principal) {
        Zone zone =  zoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zone with id '" + id + "' not found."));
        if (zoneRepository.existsByCodeAndProjectIdAndNotId(editDTO.getCode(),editDTO.getProjectId(),id)) {
            throw new EntityAlreadyExistsException("Zone with code '" + editDTO.getCode() + "' already exists in this project.");
        }
        editMapper.map(editDTO, zone);
        zoneRepository.save(zone);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!zoneRepository.existsById(id)) {
            throw new NoSuchElementException("Zone with id '" + id + "' not found");
        }
        zoneRepository.deleteById(id);
    }

    @Transactional
    public void toggleIsArchive(Integer id, Principal principal ) {
        Zone zone = zoneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Zone with id '" + id + "' not found."));
        zone.setIsArchive(zone.getIsArchive() == null || !zone.getIsArchive());
        zoneRepository.save(zone);
    }
}
