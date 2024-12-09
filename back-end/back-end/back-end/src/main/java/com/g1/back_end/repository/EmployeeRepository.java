package com.g1.back_end.repository;

import com.g1.back_end.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCentroDeCustos_IdCentroDeCustos(Long idCentroDeCustos);
}
