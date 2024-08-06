package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.groupOfObject.*;
import com.gta.spring.springboot.junix_opp.entity.GroupOfObject;
import com.gta.spring.springboot.junix_opp.exceptions.EntityAlreadyExistsException;
import com.gta.spring.springboot.junix_opp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements BaseCRUDService<GroupOfObject,
        GroupOfObjectReadDTO, GroupOfObjectEditDTO, Integer> {

    private final GroupRepository groupRepository;
    private final GroupOfObjectReadMapper readGroupMapper;
    private final GroupOfObjectWithChildrenReadMapper readGroupWithChildrenMapper;
    private final GroupOfObjectEditMapper editGroupMapper;


    @Override
    public List<GroupOfObjectReadDTO> findAll() {
                return groupRepository.findAll().stream()
                .map(readGroupMapper::map)
                .toList();
    }

    public List<GroupOfObjectWithChildrenReadDTO> findAllWithChildren() {
        return groupRepository.findAllByIsArchiveIsFalse().stream()
                .map(readGroupWithChildrenMapper::map)
                .toList();
    }

    @Override
    public Optional<GroupOfObjectReadDTO> findById(Integer id) {
        return groupRepository.findById(id)
                .map(readGroupMapper::map);
    }

    @Transactional
    public Optional<GroupOfObjectWithChildrenReadDTO> findByIdWithChildren(Integer id) {
        return groupRepository.findById(id)
                .map(readGroupWithChildrenMapper::map);
    }

    @Override
    public GroupOfObject findEntityById(Integer id){
        return Optional.ofNullable(id)
                .flatMap(groupRepository::findById)
                .orElse(null);
    }





    @Override
    @Transactional
    public void create(GroupOfObjectEditDTO editDTO, Principal principal) {
        if (groupRepository.existsByName(editDTO.getName())) {
            throw new EntityAlreadyExistsException("Group with name '" + editDTO.getName() + "' already exists.");
        }
        GroupOfObject group = editGroupMapper.map(editDTO);
         groupRepository.save(group);
    }

    @Override
    @Transactional
    public void update(Integer id, GroupOfObjectEditDTO editDTO, Principal principal) {
        GroupOfObject group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group with id '" + id + "' not found."));
        if (groupRepository.existsByNameAndNotId(editDTO.getName(),id)) {
            throw new EntityAlreadyExistsException("Group with name '" + editDTO.getName() + "' already exists.");
        }

        editGroupMapper.map(editDTO, group);
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!groupRepository.existsById(id)) {
            throw new NoSuchElementException("Group with id '" + id + "' not found");
        }
        groupRepository.deleteById(id);
    }

    @Transactional
    public void toggleIsArchive(Integer id, Principal principal ) {
        GroupOfObject group = groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group with id '" + id + "' not found."));

        group.setIsArchive(group.getIsArchive() == null || !group.getIsArchive());
        groupRepository.save(group);
    }
}
