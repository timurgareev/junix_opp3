package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingReadDTO;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionEditDTO;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionEditMapper;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import com.gta.spring.springboot.junix_opp.entity.Revision;
import com.gta.spring.springboot.junix_opp.exceptions.EntityAlreadyExistsException;
import com.gta.spring.springboot.junix_opp.repository.RevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RevisionService implements BaseCRUDService<Revision,
        RevisionReadDTO, RevisionEditDTO, Long>
{

    private final RevisionRepository revisionRepository;
    private final RevisionReadMapper readMapper;
    private final RevisionEditMapper editMapper;


    @Override
    public Revision findEntityById(Long drawingId){
        return Optional.ofNullable(drawingId)
                .flatMap(revisionRepository::findById)
                .orElse(null);
    }

//    public List<RevisionReadDTO> findAllByDrawingId(Long drawingId) {
//        return revisionRepository.findAllByDrawingId(drawingId).stream()
//                .map(revisionReadMapper::map)
//                .toList();
//    }

    @Override
    public List<RevisionReadDTO> findAll() {
        return revisionRepository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    public List<RevisionReadDTO> findAllSearch(
            String filterDrawingCode, Long drawingId, Boolean isLatest, Boolean isArchive) {
        List<Revision> revisions = revisionRepository.findAllSearch(filterDrawingCode, drawingId, isLatest, isArchive);
        return revisions.stream()
                .map(readMapper::map)
                .toList();
    }

    @Override
    public Optional<RevisionReadDTO> findById(Long id) {
        return revisionRepository.findById(id)
                .map(readMapper::map);
    }



    @Override
    @Transactional
    public void create(RevisionEditDTO editDTO, Principal principal) {
        Revision revision = editMapper.map(editDTO);
        revisionRepository.save(revision);
    }

    @Override
    @Transactional
    public void update(Long id, RevisionEditDTO editDTO, Principal principal) {
        Revision revision =  revisionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Revision with id '" + id + "' not found."));
        editMapper.map(editDTO, revision);
        revisionRepository.save(revision);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!revisionRepository.existsById(id)) {
            throw new NoSuchElementException("Revision with id '" + id + "' not found");
        }
        revisionRepository.deleteById(id);
    }

    @Transactional
    public void toggleIsArchive(Long id, Principal principal ) {
        Revision revision = revisionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Revision with id '" + id + "' not found."));
        revision.setIsArchive(revision.getIsArchive() == null || !revision.getIsArchive());
        revisionRepository.save(revision);
    }
}
