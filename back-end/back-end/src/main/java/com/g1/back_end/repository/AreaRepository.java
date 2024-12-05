package com.g1.back_end.repository;

import com.g1.back_end.domain.AreaDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<AreaDomain, Long> {
    Optional<AreaDomain> findByName(String name);
    boolean existsByName(String name);
}