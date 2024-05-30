package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.Revision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevisionRepository extends JpaRepository<Revision, Long> {
}
