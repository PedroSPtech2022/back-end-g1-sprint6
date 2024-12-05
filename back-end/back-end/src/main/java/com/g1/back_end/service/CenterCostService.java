package com.g1.back_end.service;

import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.VariableExpense;
import com.g1.back_end.dto.CostCenterDTO;
import com.g1.back_end.exception.BadRequestException;
import com.g1.back_end.mapper.CostCenterMapper;
import com.g1.back_end.repository.AreaRepository;
import com.g1.backend.dto.VariableExpenseApprovalDTO;
import com.g1.back_end.dto.VariableExpenseDTO;
import com.g1.back_end.repository.CenterCostRepository;
import com.g1.back_end.repository.EmployeeRepository;
import com.g1.back_end.repository.VariableExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;


@Service
public class CenterCostService {
    @Autowired
    private CenterCostRepository costCenterRepository;

    @Autowired
    private  VariableExpenseRepository variableExpenseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AreaRepository areaRepository;

    public CenterCost criarCenterCost(CostCenterDTO costCenterDTO) {
        AreaDomain area = areaRepository.findByName(costCenterDTO.getArea())
                .orElseThrow(() -> new BadRequestException("Area não encontrada."));

        Employee employee = employeeRepository.findByName(costCenterDTO.getResponsible())
                .orElseThrow(() -> new BadRequestException("Area não encontrada."));

        if(costCenterRepository.existsByNameAndArea(costCenterDTO.getName(),area)){
            throw new BadRequestException("Centro de custos já existe nesta área.");
        }

        CenterCost centerCost = CostCenterMapper.dtoToCenterCost(costCenterDTO,area, employee);
        return costCenterRepository.save(centerCost);
    }

    // Adiciona uma despesa variável a um centro de custo
    public void addVariableExpense(Long costCenterId, VariableExpenseDTO variableExpenseDTO) {
        CenterCost costCenter = costCenterRepository.findById(costCenterId)
                .orElseThrow(() -> new RuntimeException("Centro de Custo não encontrado"));
        VariableExpense variableExpense = new VariableExpense();
        variableExpense.setType(variableExpenseDTO.getType());
        variableExpense.setDescribe(variableExpenseDTO.getDescribe());
        variableExpense.setValue(variableExpenseDTO.getValue());
        variableExpense.setDate(variableExpenseDTO.getDate());
        variableExpense.setResponsibile(variableExpenseDTO.getResponsible());
        variableExpense.setCategory(variableExpenseDTO.getCategory());
        variableExpense.setPaymentMethod(variableExpenseDTO.getPaymentMethod());
        variableExpense.setObservation(variableExpenseDTO.getObservation());
        variableExpense.setApproval(variableExpenseDTO.getApproval());
        variableExpense.setCostCenter(costCenter);

        variableExpenseRepository.save(variableExpense);
    }

    // Aprova uma despesa variável
    @Transactional
    public void approveVariableExpense(VariableExpenseApprovalDTO approvalDTO) {
        VariableExpense variableExpense = variableExpenseRepository.findByDateAndResponsibile(
                        approvalDTO.getDate(), approvalDTO.getResponsible())
                .orElseThrow(() -> new RuntimeException("Despesa variável não encontrada"));

        variableExpense.setApproval(approvalDTO.getApproved());
        variableExpenseRepository.save(variableExpense);
    }

    // Recupera despesas variáveis por centro de custo
    public List<VariableExpenseDTO> getVariableExpensesByCostCenter(Long costCenterId) {
        List<VariableExpense> variableExpenses = variableExpenseRepository.findByCostCenterId(costCenterId);
        return variableExpenses.stream()
                .map(ve -> new VariableExpenseDTO(ve.getType(), ve.getDescribe(), ve.getValue(),
                        ve.getDate(), ve.getResponsibile(), ve.getCategory(), ve.getPaymentMethod(),
                        ve.getObservation(), ve.getApproval()))
                .collect(Collectors.toList());
    }

    // Recupera despesas variáveis por funcionário
    public List<VariableExpenseDTO> getVariableExpensesByEmployee(String employeeName) {
        List<VariableExpense> variableExpenses = variableExpenseRepository.findByEmployeeName(employeeName);
        return variableExpenses.stream()
                .map(ve -> new VariableExpenseDTO(ve.getType(), ve.getDescribe(), ve.getValue(),
                        ve.getDate(), ve.getResponsibile(), ve.getCategory(), ve.getPaymentMethod(),
                        ve.getObservation(), ve.getApproval()))
                .collect(Collectors.toList());
    }


}
// Recupera os detalhes de um centro de custo
// public CostCenterDTO getCostCenterById(Long executiveId) {
//    CenterCost costCenter = costCenterRepository.findById(executiveId)
//           .orElseThrow(() -> new RuntimeException("Centro de Custo não encontrado"));
//
//   return new CostCenterDTO(costCenter.getArea(), costCenter.getCostCenterName(), costCenter.getAnnualBudget(),
//            costCenter.getType());
//  }
//  }
