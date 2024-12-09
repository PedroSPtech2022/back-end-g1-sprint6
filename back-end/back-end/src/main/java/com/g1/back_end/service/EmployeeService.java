package com.g1.back_end.service;

import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.dto.EmployeeDTO;
import com.g1.back_end.exception.BadRequestException;
import com.g1.back_end.mapper.EmployeeMapper;
import com.g1.back_end.repository.CenterCostRepository;
import com.g1.back_end.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CenterCostRepository centerCostRepository;

    /**
     * Creates a new employee and associates it with a cost center.
     *
     * @param employeeDTO the employee data.
     * @return the created employee.
     */
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO, Long costCenterId) {
        // Find the CenterCost by ID
        CenterCost centerCost = centerCostRepository.findById(costCenterId)
                .orElseThrow(() -> new BadRequestException("Centro de custo não encontrado com ID: " + costCenterId));

        // Map DTO to entity
        Employee employee = EmployeeMapper.dtoToEmployee(employeeDTO, centerCost);
        employee = employeeRepository.save(employee);

        // Return the created employee as DTO
        return EmployeeMapper.employeeToDTO(employee);
    }

    /**
     * Retrieves all employees associated with a specific cost center.
     *
     * @param costCenterId the cost center ID.
     * @return the list of employees.
     */
    public List<EmployeeDTO> getEmployeesByCostCenter(Long costCenterId) {
        List<Employee> employees = employeeRepository.findByCostCenterId(costCenterId);
        return employees.stream()
                .map(EmployeeMapper::employeeToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the ID of the employee.
     * @return the employee data.
     */
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Funcionário não encontrado com ID: " + id));
        return EmployeeMapper.employeeToDTO(employee);
    }

    /**
     * Updates an existing employee's details.
     *
     * @param id             the ID of the employee to update.
     * @param employeeDTO    the new employee data.
     * @param costCenterId   the ID of the cost center.
     * @return the updated employee data.
     */
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO, Long costCenterId) {
        // Find the existing employee
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Funcionário não encontrado com ID: " + id));

        // Find the CenterCost by ID
        CenterCost centerCost = centerCostRepository.findById(costCenterId)
                .orElseThrow(() -> new BadRequestException("Centro de custo não encontrado com ID: " + costCenterId));

        // Update the employee with new values
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setJobTitle(employeeDTO.getJobTitle());
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employee.setCenterCost(centerCost);

        // Save the updated employee
        employee = employeeRepository.save(employee);

        return EmployeeMapper.employeeToDTO(employee);
    }

    /**
     * Deletes an employee by their ID.
     *
     * @param id the ID of the employee to delete.
     */
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new BadRequestException("Funcionário não encontrado com ID: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
