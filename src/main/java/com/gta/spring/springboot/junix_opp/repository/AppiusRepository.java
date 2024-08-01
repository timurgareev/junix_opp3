package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.Appius;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppiusRepository extends JpaRepository<Appius, Long> {
}
