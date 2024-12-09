package com.g1.back_end.repository;

import com.g1.back_end.domain.AreaDomain;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<AreaDomain, Long> {
    Optional<AreaDomain> findByArea(Long area);
    boolean existsByNomeArea(String nomeArea);
}