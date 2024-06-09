package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByDrawingId(Long id);

    @Query("SELECT d FROM Task d WHERE d.project.id = :id")
    List<Task> findAllByProjectsId(@Param("id") Integer id);
}
