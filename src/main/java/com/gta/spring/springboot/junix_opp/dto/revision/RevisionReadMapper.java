package com.gta.spring.springboot.junix_opp.dto.revision;


import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Revision;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RevisionReadMapper implements Mapper<Revision, RevisionReadDTO> {

    @Override
    public RevisionReadDTO map(Revision object) {
        return new RevisionReadDTO(
                object.getId(),
                object.getDrawing().getId(),
                object.getDrawing().getCode(),
                object.getName(),
                object.getStatus(),
                object.getDateInbox(),
                object.getDateOutbox(),
                object.getInproductionDateSystem(),
                object.getInproductionDateManual(),
                object.getRateNumber(),
                object.getIsLatest(),
                object.getComment1(),
                object.getComment2(),
                object.getIsArchive(),
                object.getIsOnDelete(),
                object.getIsProcced()
        );
    }

}
