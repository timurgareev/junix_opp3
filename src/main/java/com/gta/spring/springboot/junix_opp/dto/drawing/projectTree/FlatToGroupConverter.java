package com.gta.spring.springboot.junix_opp.dto.drawing.projectTree;

import com.gta.spring.springboot.junix_opp.dto.drawing.DrawingLightReadDTO;
import com.gta.spring.springboot.junix_opp.dto.groupOfObject.GroupOfObjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.dto.object.ObjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.dto.zone.ZoneWithChildrenReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FlatToGroupConverter {
    public GroupOfObjectWithChildrenReadDTO convert(List<FlatDrawingDTO> flatDrawings) {
        // Группировка по Object
        Map<Integer, List<FlatDrawingDTO>> objectsMap = flatDrawings.stream()
                .collect(Collectors.groupingBy(FlatDrawingDTO::getObjectId));

        // Преобразование в объекты с детьми
        List<ObjectWithChildrenReadDTO> objects = objectsMap.entrySet().stream()
                .map(entry -> {
                    Integer objectId = entry.getKey();
                    List<FlatDrawingDTO> drawingsForObject = entry.getValue();

                    // Группировка по Project
                    Map<Integer, List<FlatDrawingDTO>> projectsMap = drawingsForObject.stream()
                            .collect(Collectors.groupingBy(FlatDrawingDTO::getProjectId));

                    // Преобразование в проекты с детьми
                    List<ProjectWithChildrenReadDTO> projects = projectsMap.entrySet().stream()
                            .map(projectEntry -> {
                                Integer projectId = projectEntry.getKey();
                                List<FlatDrawingDTO> drawingsForProject = projectEntry.getValue();

                                // Группировка по Zone
                                Map<Integer, List<FlatDrawingDTO>> zonesMap = drawingsForProject.stream()
                                        .collect(Collectors.groupingBy(FlatDrawingDTO::getZoneId));

                                // Преобразование в зоны с детьми
                                List<ZoneWithChildrenReadDTO> zones = zonesMap.entrySet().stream()
                                        .map(zoneEntry -> {
                                            Integer zoneId = zoneEntry.getKey();
                                            List<FlatDrawingDTO> drawingsForZone = zoneEntry.getValue();

                                            // Преобразование в DrawingLightReadDTO
                                            List<DrawingLightReadDTO> drawings = drawingsForZone.stream()
                                                    .map(dto -> new DrawingLightReadDTO(dto.getDrawingId(), dto.getDrawingCode(), dto.getDrawingIsArchive()))
                                                    .collect(Collectors.toList());

                                            return new ZoneWithChildrenReadDTO(
                                                    zoneId,
                                                    drawingsForZone.get(0).getZoneName(), // Имя зоны
                                                    drawingsForZone.get(0).getZoneCode(), // Код зоны
                                                    drawingsForZone.get(0).getZoneIsArchive(), // Архивность зоны
                                                    drawings
                                            );
                                        })
                                        .collect(Collectors.toList());

                                return new ProjectWithChildrenReadDTO(
                                        projectId,
                                        drawingsForProject.get(0).getProjectName(), // Имя проекта
                                        drawingsForProject.get(0).getProjectIsArchive(), // Архивность проекта
                                        zones
                                );
                            })
                            .collect(Collectors.toList());

                    return new ObjectWithChildrenReadDTO(
                            objectId,
                            drawingsForObject.get(0).getObjectName(), // Имя объекта
                            drawingsForObject.get(0).getObjectIsArchive(), // Архивность объекта
                            projects
                    );
                })
                .collect(Collectors.toList());

        // В данном примере создается объект GroupOfObjectWithChildrenReadDTO с одним элементом в списке объектов
        return new GroupOfObjectWithChildrenReadDTO(
                flatDrawings.get(0).getObjectId(), // Используйте реальные данные для заполнения
                flatDrawings.get(0).getObjectName(), // Имя объекта
                flatDrawings.get(0).getObjectIsArchive(), // Архивность объекта
                objects
        );
    }
}
