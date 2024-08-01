package com.gta.spring.springboot.junix_opp.dto.zone;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectEditDTO2;
import com.gta.spring.springboot.junix_opp.entity.Project;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import com.gta.spring.springboot.junix_opp.servise.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZoneEditMapper2 implements Mapper<ZoneEditDTO2, Zone> {

    private final ProjectService projectService;
    @Override
    public Zone map(ZoneEditDTO2 object) {
        Zone zone = new Zone();
        copy(object, zone);
        return zone;
    }

    @Override
    public Zone map(ZoneEditDTO2 fromObject, Zone toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ZoneEditDTO2 object, Zone zone) {
        zone.setName(object.getName());
        zone.setFullname(object.getFullName());
        zone.setCode(object.getCode());
        zone.setOrdernumber(object.getOrdernumber());
        zone.setDeclaration(object.getDeclaration());
        zone.setComment(object.getComment());
        zone.setProject(projectService.findProjectById(object.getProjectId()));
        zone.setIsArchive(object.getIsArchive());
        zone.setIsOnDelete(object.getIsOnDelete());
    }
}
