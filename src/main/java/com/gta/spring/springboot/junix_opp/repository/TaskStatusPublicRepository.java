package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.TaskStatusPrivate;
import com.gta.spring.springboot.junix_opp.entity.TaskStatusPublic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusPublicRepository extends JpaRepository<TaskStatusPublic,Integer> {

}
