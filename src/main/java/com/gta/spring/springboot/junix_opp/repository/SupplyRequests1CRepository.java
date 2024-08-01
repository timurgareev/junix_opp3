package com.gta.spring.springboot.junix_opp.repository;

import com.gta.spring.springboot.junix_opp.entity.SupplyRequests1C;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyRequests1CRepository extends JpaRepository<SupplyRequests1C,Long> {
}
