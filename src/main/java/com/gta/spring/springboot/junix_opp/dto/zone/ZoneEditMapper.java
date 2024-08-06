package com.gta.spring.springboot.junix_opp.dto.zone;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import com.gta.spring.springboot.junix_opp.servise.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ZoneEditMapper implements Mapper<ZoneEditDTO, Zone> {

    private final ProjectService projectService;
    @Override
    public Zone map(ZoneEditDTO object) {
        Zone zone = new Zone();
        copy(object, zone);
        return zone;
    }

    @Override
    public Zone map(ZoneEditDTO fromObject, Zone toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(ZoneEditDTO object, Zone zone) {
        zone.setName(object.getName());
        zone.setFullname(object.getFullName());
        zone.setCode(object.getCode());
        zone.setOrdernumber(object.getOrdernumber());
        zone.setDeclaration(object.getDeclaration());
        zone.setComment(object.getComment());
        zone.setProject(projectService.findEntityById(object.getProjectId()));

    }
}
