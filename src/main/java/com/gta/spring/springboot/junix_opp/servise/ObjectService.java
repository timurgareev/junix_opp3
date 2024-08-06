package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.object.ObjectEditDTO;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectEditMapper;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectReadMapper;
import com.gta.spring.springboot.junix_opp.entity.GroupOfObject;
import com.gta.spring.springboot.junix_opp.entity.Object;
import com.gta.spring.springboot.junix_opp.exceptions.EntityAlreadyExistsException;
import com.gta.spring.springboot.junix_opp.repository.ObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjectService implements BaseCRUDService<Object,
        ObjectReadDTO, ObjectEditDTO, Integer> {

    private final ObjectRepository objectRepository;
    private final ObjectReadMapper readMapper;
    private final ObjectEditMapper editMapper;

    @Override
    public List<ObjectReadDTO> findAll() {
        return objectRepository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    @Override
    public Optional<ObjectReadDTO> findById(Integer id) {
        return objectRepository.findById(id)
                .map(readMapper::map);
    }

    @Override
    public Object findEntityById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(objectRepository::findById)
                .orElse(null);
    }

    @Override
    @Transactional
    public void create(ObjectEditDTO editDTO, Principal principal) {
        if (objectRepository.existsByName(editDTO.getName())) {
            throw new EntityAlreadyExistsException("Object with name '" + editDTO.getName() + "' already exists.");
        }
        Object obj = editMapper.map(editDTO);
        objectRepository.save(obj);
    }

    @Override
    @Transactional
    public void update(Integer id, ObjectEditDTO editDTO, Principal principal) {
        Object obj = objectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Object with id '" + id + "' not found."));
        if (objectRepository.existsByNameAndNotId(editDTO.getName(),id)) {
            throw new EntityAlreadyExistsException("Object with name '" + editDTO.getName() + "' already exists.");
        }

        editMapper.map(editDTO, obj);
        objectRepository.save(obj);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!objectRepository.existsById(id)) {
            throw new NoSuchElementException("Object with id '" + id + "' not found");
        }
        objectRepository.deleteById(id);
        }


    @Transactional
    public void toggleIsArchive(Integer id, Principal principal ) {
        Object obj = objectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Object with id '" + id + "' not found."));
        obj.setIsArchive(obj.getIsArchive() == null || !obj.getIsArchive());
        objectRepository.save(obj);
    }
}
