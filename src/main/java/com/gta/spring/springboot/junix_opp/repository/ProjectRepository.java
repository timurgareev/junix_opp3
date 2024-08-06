package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.dto.Mapper;
import com.gta.spring.springboot.junix_opp.dto.project.ProjectWithChildrenReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    boolean existsByName(String name);

    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END FROM Project o WHERE o.name = :name AND o.id <> :id")
    boolean existsByNameAndNotId(@Param("name") String name, @Param("id") Integer id);


    @Query("select o From Project o Left Join fetch o.object")
    List<Project> findAll();

    @Query("select o From Project o Left Join fetch o.object where o.id = :id")
    Optional<Project> findById(Integer id);


//    @Query ("select p from Project p left join fetch p.zones z left join fetch z.drawings d where p.id=:id")

//    @EntityGraph(attributePaths = {"zones", "zones.drawings"})
//    @Query("select p from Project p where p.id = :id")
//    Optional<Project> findAllWithChildren(@Param("id") Integer id);

}
