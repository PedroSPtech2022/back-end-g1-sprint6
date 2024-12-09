package com.g1.back_end.repository;

import com.g1.back_end.domain.VariableExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VariableExpenseRepository extends JpaRepository<VariableExpense, Long> {
    List<VariableExpense> findByCostCenterId(Long costCenterId);
    List<VariableExpense> findByEmployeeName(String employeeName);
    Optional<VariableExpense> findByDateAndResponsibile(LocalDate date, String responsible);
    List<VariableExpense> findByResponsibileName(String responsibleName);


}
