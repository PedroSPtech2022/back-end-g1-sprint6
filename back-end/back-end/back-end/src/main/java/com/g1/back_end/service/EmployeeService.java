package com.g1.back_end.service;

import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.User;
import com.g1.back_end.dto.EmployeeDTO;
import com.g1.back_end.mapper.EmployeeMapper;
import com.g1.back_end.repository.CenterCostRepository;
import com.g1.back_end.repository.EmployeeRepository;
import com.g1.back_end.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CenterCostRepository centerCostRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Employee> getEmployeesByCenterCost(Long centerCostId) {
        return employeeRepository.findByCentroDeCustos_IdCentroDeCustos(centerCostId);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Optional<CenterCost> centerCost = centerCostRepository.findById(employeeDTO.getCenterCostId());
        Optional<User> user = userRepository.findById(employeeDTO.getUserId());

        if (centerCost.isEmpty() || user.isEmpty()) {
            throw new RuntimeException("Centro de custos ou usuário não encontrado");
        }

        Employee employee = EmployeeMapper.dtoToEmployee(employeeDTO, centerCost.get(), user.get());
        employee = employeeRepository.save(employee);

        return EmployeeMapper.employeeToDTO(employee);
    }

    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        employeeRepository.deleteById(employeeId);
    }
}
