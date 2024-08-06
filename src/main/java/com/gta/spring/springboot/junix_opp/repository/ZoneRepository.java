package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.Object;
import com.gta.spring.springboot.junix_opp.entity.Project;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ZoneRepository extends JpaRepository<Zone, Integer> {

//    boolean existsByName(String name);
//
//    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END FROM Zone o WHERE o.name = :name AND o.id <> :id")
//    boolean existsByNameAndNotId(@Param("name") String name, @Param("id") Integer id);


    @Query("select o From Zone o Left Join fetch o.project")
    List<Zone> findAll();

    @Query("select o From Zone o Left Join fetch o.project where o.id = :id")
    Optional<Zone> findById(Integer id);



    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN TRUE ELSE FALSE END FROM Zone z JOIN z.project p WHERE z.code = :code AND p.id = :projectId")
    boolean existsByCodeAndProjectId(String code, Integer projectId);



    @Query("SELECT CASE WHEN COUNT(z) > 0 THEN TRUE ELSE FALSE END FROM Zone z JOIN z.project p WHERE z.code = :code AND p.id = :projectId AND z.id <> :id")
    boolean existsByCodeAndProjectIdAndNotId(String code, Integer projectId, Integer id);


}
