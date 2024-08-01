package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.DrawingsMark;
import com.gta.spring.springboot.junix_opp.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawingMarkRepository extends JpaRepository<DrawingsMark, Integer> {
}
