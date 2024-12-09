package com.g1.back_end.repository;

import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.domain.CenterCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.geom.Area;


@Repository
public interface CenterCostRepository extends JpaRepository<CenterCost, Long> {
    boolean existsByNomeCentroAndArea(String name, AreaDomain area);
}
