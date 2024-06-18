package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.entity.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findAllByDrawingId(Long id);

    @EntityGraph(attributePaths = {"tasks"})
    @Query("SELECT e FROM Event e LEFT JOIN FETCH e.tasks WHERE e.drawing.id = :drawingId")
    List<Event> findAllByDrawingIdWithTasks(Long drawingId);
}
