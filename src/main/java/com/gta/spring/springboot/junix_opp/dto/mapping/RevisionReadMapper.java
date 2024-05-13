package com.gta.spring.springboot.junix_opp.dto.mapping;


import com.gta.spring.springboot.junix_opp.dto.dto.DisciplineReadDTO;
import com.gta.spring.springboot.junix_opp.dto.dto.RevisionReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Discipline;
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
                object.getName(),
                object.getStatus(),
                object.getDateInbox(),
                object.getDateOutbox(),
                object.getInproductionDateSystem(),
                object.getInproductionDateManual(),
                object.getRateNumber(),
                object.isLatest(),
                object.getComment1(),
                object.getComment2()
        );
    }

}
