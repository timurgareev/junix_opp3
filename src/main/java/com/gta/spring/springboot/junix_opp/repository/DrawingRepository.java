package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.dto.dto.DrawingWithRevisionsReadDTO;
import com.gta.spring.springboot.junix_opp.entity.Drawing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DrawingRepository extends JpaRepository<Drawing,Long> {

//    List<Drawing> findByCodeContaining(String code_normal);

//    @Query("SELECT d FROM Drawing d Join fetch d.revisions r WHERE d.code LIKE %:code%") - ошибка - не выдает шфиры без ревизий
//    @Query("SELECT d FROM Drawing d WHERE d.codeNormal LIKE %:codeNormal%")
//@Query("SELECT d FROM Drawing d left Join fetch d.revisions r WHERE d.code LIKE %:code%")

//    финальный вариант: одним запросом тянет из трех таблиц
//@Query("SELECT d FROM Drawing d left Join fetch d.revisions r left join fetch d.discipline WHERE d.code LIKE %:code%")
//List<Drawing> findByCodeContaining(@Param("code") String code);

}
