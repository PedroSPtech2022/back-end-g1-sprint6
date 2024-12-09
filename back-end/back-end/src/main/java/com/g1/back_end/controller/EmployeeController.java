package com.g1.back_end.controller;

import com.g1.back_end.dto.EmployeeDTO;
import com.g1.back_end.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Creates a new employee and associates it with a cost center.
     *
     * @param employeeDTO the employee data.
     * @param costCenterId the ID of the cost center.
     * @return the created employee.
     */
    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @RequestParam Long costCenterId) {
        EmployeeDTO createdEmployee = employeeService.createEmployee(employeeDTO, costCenterId);
        return ResponseEntity.status(201).body(createdEmployee);
    }

    /**
     * Retrieves all employees associated with a specific cost center.
     *
     * @param costCenterId the ID of the cost center.
     * @return a list of employees.
     */
    @GetMapping("/cost-center/{costCenterId}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByCostCenter(@PathVariable Long costCenterId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByCostCenter(costCenterId);
        return ResponseEntity.ok(employees);
    }

    /**
     * Retrieves a specific employee by ID.
     *
     * @param id the ID of the employee.
     * @return the employee data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDTO);
    }

    /**
     * Updates an existing employee by ID.
     *
     * @param id             the ID of the employee to update.
     * @param employeeDTO    the new employee data.
     * @param costCenterId   the ID of the cost center.
     * @return the updated employee data.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO, @RequestParam Long costCenterId) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO, costCenterId);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the ID of the employee to delete.
     * @return a response indicating the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}

