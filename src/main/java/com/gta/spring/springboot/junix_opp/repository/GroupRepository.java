package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.dto.drawing.projectTree.FlatDrawingDTO;
import com.gta.spring.springboot.junix_opp.entity.GroupOfObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupOfObject, Integer> {

    boolean existsByName(String name);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN TRUE ELSE FALSE END FROM GroupOfObject g WHERE g.name = :name AND g.id <> :id")
    boolean existsByNameAndNotId(@Param("name") String name, @Param("id") Integer id);

    @Query("SELECT g FROM GroupOfObject g order by g.ordernumber asc ")
    List<GroupOfObject> findAll();

    List<GroupOfObject> findAllByIsArchiveIsFalse();







}
