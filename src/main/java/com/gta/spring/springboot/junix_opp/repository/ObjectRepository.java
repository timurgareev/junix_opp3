package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.dto.object.ObjectReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Object;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ObjectRepository extends JpaRepository<Object, Integer> {

    boolean existsByName(String name);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END FROM Object o WHERE o.name = :name AND o.id <> :id")
    boolean existsByNameAndNotId(@Param("name") String name, @Param("id") Integer id);


//    @EntityGraph(attributePaths = "objects.group_of_objects")
    @Query("select o From Object o Left Join fetch o.group_of_objects")
    List<Object> findAll();


    @Query("select o From Object o Left Join fetch o.group_of_objects where o.id = :id")
    Optional<Object> findById(Integer id);
}
