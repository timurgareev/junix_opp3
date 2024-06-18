package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.SupplyRequest;
import com.gta.spring.springboot.junix_opp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplyRequestRepository extends JpaRepository<SupplyRequest,Long> {

    @Query("SELECT d FROM SupplyRequest d WHERE d.drawing.id = :id")
    List<SupplyRequest> findAllByDrawingId(@Param("id") Long id);
}
