package com.g1.back_end.repository;

import com.g1.back_end.domain.OrcamentoAnualDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrcamentoAnualRepository  extends JpaRepository<OrcamentoAnualDomain,Long> {
    Optional<OrcamentoAnualDomain> findById(Long id);
}
