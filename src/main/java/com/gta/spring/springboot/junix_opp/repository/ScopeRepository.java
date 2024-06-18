package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.Event;
import com.gta.spring.springboot.junix_opp.entity.Scope;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScopeRepository extends JpaRepository<Scope, Long> {


    @Query("SELECT e FROM Scope e WHERE e.drawing.id = :drawingId")
    List<Scope> findAllByDrawingId(Long drawingId);
}
