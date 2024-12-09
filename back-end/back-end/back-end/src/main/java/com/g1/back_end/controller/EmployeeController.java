package com.g1.back_end.controller;

import com.g1.back_end.domain.Employee;
import com.g1.back_end.dto.EmployeeDTO;
import com.g1.back_end.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Endpoint para buscar funcionários por centro de custos
    @GetMapping("/center-cost/{centerCostId}")
    public ResponseEntity<List<Employee>> getEmployeesByCenterCost(@PathVariable Long centerCostId) {
        List<Employee> employees = employeeService.getEmployeesByCenterCost(centerCostId);
        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(employees);
    }

    // Endpoint para criar um novo funcionário
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO);
        return ResponseEntity.ok(createdEmployee);
    }

    // Endpoint para deletar um funcionário
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}

