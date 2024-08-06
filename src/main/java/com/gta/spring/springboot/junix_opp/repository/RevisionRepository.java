package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.Revision;
import com.gta.spring.springboot.junix_opp.entity.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RevisionRepository extends JpaRepository<Revision, Long> {

//    @Query("SELECT r FROM Revision r WHERE r.drawing.id = :id")
//    List<Revision> findAllByDrawingId(@Param("id") Long id);

@EntityGraph(attributePaths = {"drawing"})
//    @Query("SELECT r FROM Revision r LEFT JOIN FETCH r.drawing d WHERE r.id = :id")
    Optional<Revision> findById(@Param("id") Long id);

    @Query("SELECT r FROM Revision r " +
            "LEFT JOIN FETCH r.drawing d " +
            "WHERE (:filterDrawingCode IS NULL OR d.code LIKE %:filterDrawingCode%) " +
            "AND (:drawingId IS NULL OR d.id = :drawingId) " +
            "AND (:isLatest IS NULL OR r.isLatest = :isLatest) " +
            "AND (:isArchive IS NULL OR r.isArchive = :isArchive)")
    List<Revision> findAllSearch(@Param("filterDrawingCode") String filterDrawingCode,
                                 @Param("drawingId") Long drawingId,
                                 @Param("isLatest") Boolean isLatest,
                                 @Param("isArchive") Boolean isArchive);

}
