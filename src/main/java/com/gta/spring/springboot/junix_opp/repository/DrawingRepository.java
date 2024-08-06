package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.dto.drawing.projectTree.FlatDrawingDTO;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DrawingRepository extends JpaRepository<Drawing,Long> {

//    List<Drawing> findByCodeContaining(String code_normal);

//    @Query("SELECT d FROM Drawing d Join fetch d.revisions r WHERE d.code LIKE %:code%") - ошибка - не выдает шфиры без ревизий
//    @Query("SELECT d FROM Drawing d WHERE d.codeNormal LIKE %:codeNormal%")
//@Query("SELECT d FROM Drawing d left Join fetch d.revisions r WHERE d.code LIKE %:code%")

//    финальный вариант: одним запросом тянет из трех таблиц
//@Query("SELECT d FROM Drawing d left Join fetch d.revisions r left join fetch d.discipline WHERE d.code LIKE %:code%")
//List<Drawing> findByCodeContaining(@Param("code") String code);

    @Query("SELECT d FROM Drawing d  Join FETCH d.zone z WHERE z.project.id = :projectId")
    List<Drawing> findAllByProjectId(Integer projectId);



    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END FROM Drawing d WHERE d.code = :code")
    boolean existsByCode(String code);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END FROM Drawing d WHERE d.code = :code AND d.id <> :id")
    boolean existsByCodeAndNotId(String code, Long id);

    @Query("SELECT d FROM Drawing d   WHERE d.code ilike %:shifrString%")
    List<Drawing> findByCodeContaining(String shifrString);

    @Query("SELECT d FROM Drawing d " +
            "LEFT JOIN FETCH d.drawingsMark dm " +
            "LEFT JOIN FETCH dm.mark m " +
            "LEFT JOIN FETCH m.discipline dis " +
            "LEFT JOIN FETCH dis.groupDiscipline gd " +
            "LEFT JOIN FETCH d.zone z " +
            "LEFT JOIN FETCH z.project p " +
            "WHERE (:filterCode IS NULL OR d.code LIKE %:filterCode%) " +
            "AND (:projectId IS NULL OR z.project.id = :projectId) " +
            "AND (:zoneId IS NULL OR z.id = :zoneId) " +
            "AND (:isArchive IS NULL OR d.isArchive = :isArchive)")
    List<Drawing> findAllSearch(@Param("filterCode") String filterCode,
                                @Param("projectId") Integer projectId,
                                @Param("zoneId") Integer zoneId,
                                @Param("isArchive") Boolean isArchive);

    @Query("SELECT new com.gta.spring.springboot" +
            ".junix_opp.dto.drawing.projectTree" +
            ".FlatDrawingDTO(" +
            "d.id, d.code, d.isArchive, " +
            "z.id, z.name, z.code, z.isArchive, " +
            "p.id, p.name, p.isArchive, " +
            "o.id, o.name, o.isArchive," +
            "g.id, g.name, g.isArchive) " +
            "FROM Drawing d " +
            "JOIN  d.zone z " +
            "JOIN  z.project p " +
            "JOIN  p.object o " +
            "JOIN  o.group_of_objects g " +
            "WHERE g.id = :groupId")
    List<FlatDrawingDTO> findFlatDrawingsByGroupId(@Param("groupId") Integer projectId);
}
