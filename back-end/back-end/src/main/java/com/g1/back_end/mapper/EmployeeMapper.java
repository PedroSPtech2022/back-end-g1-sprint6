package com.g1.back_end.mapper;

import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public static Employee dtoToEmployee(EmployeeDTO employeeDTO, CenterCost centerCost) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setJobTitle(employeeDTO.getJobTitle());
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        employee.setCenterCost(centerCost); // Relacionamento com CenterCost
        return employee;
    }

    public static EmployeeDTO employeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setJobTitle(employee.getJobTitle());
        employeeDTO.setPosition(employee.getPosition());
        employeeDTO.setSalary(employee.getSalary());
        return employeeDTO;
    }
}
