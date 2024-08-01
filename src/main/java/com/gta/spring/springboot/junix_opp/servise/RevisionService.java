package com.gta.spring.springboot.junix_opp.servise;

import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.dto.revision.RevisionReadMapper;
import com.gta.spring.springboot.junix_opp.entity.Revision;
import com.gta.spring.springboot.junix_opp.repository.RevisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RevisionService {

    private final RevisionRepository revisionRepository;
    private final RevisionReadMapper revisionReadMapper;

    public Revision findRevisionById(Long drawingId){
        return Optional.ofNullable(drawingId)
                .flatMap(revisionRepository::findById)
                .orElse(null);
    }

    public List<RevisionReadDTO> findAllByDrawingId(Long drawingId) {
        return revisionRepository.findAllByDrawingId(drawingId).stream()
                .map(revisionReadMapper::map)
                .toList();
    }
}
