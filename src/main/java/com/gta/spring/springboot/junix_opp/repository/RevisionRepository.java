package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.Revision;
import com.gta.spring.springboot.junix_opp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RevisionRepository extends JpaRepository<Revision, Long> {

    @Query("SELECT r FROM Revision r WHERE r.drawing.id = :id")
    List<Revision> findAllByDrawingId(@Param("id") Long id);
}
