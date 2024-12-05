package com.g1.back_end.repository;

import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByCostCenterId(Long costCenterId);

    Optional<Employee> findByName(String name);
    boolean existsByName(String name);
}
