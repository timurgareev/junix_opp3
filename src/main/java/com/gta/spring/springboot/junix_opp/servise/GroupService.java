package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectReadMapper;
import com.gta.spring.springboot.junix_opp.entity.GroupOfObject;
import com.gta.spring.springboot.junix_opp.exceptions.UserExistException;
import com.gta.spring.springboot.junix_opp.repository.GroupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupOfObjectReadMapper groupOfObjectReadMapper;

    public List<GroupOfObjectReadDTO> findAll() {
        return groupRepository.findAll().stream()
                .map(groupOfObjectReadMapper::map)
                .toList();
    }

    public Optional<GroupOfObjectReadDTO> findById(Integer id) {
        return groupRepository.findById(id)
                .map(groupOfObjectReadMapper::map);
    }

    public GroupOfObject findGroupOfObjectById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(groupRepository::findById)
                .orElse(null);
    }
}
