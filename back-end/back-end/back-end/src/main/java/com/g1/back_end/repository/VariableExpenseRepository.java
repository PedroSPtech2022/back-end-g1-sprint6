package com.g1.back_end.repository;

import com.g1.back_end.domain.VariableExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface VariableExpenseRepository extends JpaRepository<VariableExpense, Long> {
    Optional<VariableExpense> findByDataAndResponsavel(LocalDate date, String responsible);
    List<VariableExpense> findByResponsavel(String responsibleName);

}
