package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.GroupOfObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupOfObject, Integer> {
}
