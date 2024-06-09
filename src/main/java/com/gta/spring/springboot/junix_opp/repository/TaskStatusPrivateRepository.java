package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.TaskStatusPrivate;
import com.gta.spring.springboot.junix_opp.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusPrivateRepository extends JpaRepository<TaskStatusPrivate,Integer> {

}
