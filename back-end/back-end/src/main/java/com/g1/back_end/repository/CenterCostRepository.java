package com.g1.back_end.repository;

import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.domain.CenterCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.geom.Area;

public interface CenterCostRepository extends JpaRepository<CenterCost, Long> {
    boolean existsByNameAndArea(String name, AreaDomain area);
}
