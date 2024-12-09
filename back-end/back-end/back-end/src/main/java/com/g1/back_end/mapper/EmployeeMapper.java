package com.g1.back_end.mapper;

import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.User;
import com.g1.back_end.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    // Converte EmployeeDTO para Employee
    public static Employee dtoToEmployee(EmployeeDTO employeeDTO, CenterCost centerCost, User user) {
        Employee employee = new Employee();
        employee.setNome(employeeDTO.getNome());
        employee.setEmail(employeeDTO.getEmail());
        employee.setCargo(employeeDTO.getCargo());
        employee.setSenioridade(employeeDTO.getSenioridade());
        employee.setSalario(employeeDTO.getSalario());
        employee.setCentroDeCustos(centerCost); // Referência do centro de custos
        employee.setUsuario(user); // Referência do usuário
        return employee;
    }

    // Converte Employee para EmployeeDTO
    public static EmployeeDTO employeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setNome(employee.getNome());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setCargo(employee.getCargo());
        employeeDTO.setSenioridade(employee.getSenioridade());
        employeeDTO.setSalario(employee.getSalario());
        employeeDTO.setCenterCostId(employee.getCentroDeCustos() != null ? employee.getCentroDeCustos().getIdCentroDeCustos() : null);
        employeeDTO.setUserId(employee.getUsuario() != null ? employee.getUsuario().getId_usuario(): null);
        return employeeDTO;
    }
}
