package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.dto.GroupOfObjectReadDTO;
import com.gta.spring.springboot.junix_opp.dto.mapping.GroupOfObjectReadMapper;
import com.gta.spring.springboot.junix_opp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupOfObjectReadMapper groupOfObjectReadMapper;


    public List<GroupOfObjectReadDTO> findAll() {
        return groupRepository.findAll().stream()
                .map(groupOfObjectReadMapper::map)
                .toList();
    }

    public Optional<GroupOfObjectReadDTO> findById(Integer id) {
        return groupRepository.findById(id)
                .map(groupOfObjectReadMapper::map);
    }


}
