package com.g1.back_end.service;

import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.VariableExpense;
import com.g1.back_end.dto.VariableExpenseDTO;
import com.g1.back_end.dto.VariableExpenseApprovalDTO;
import com.g1.back_end.exception.BadRequestException;
import com.g1.back_end.mapper.VariableExpenseMapper;
import com.g1.back_end.mapper.VariableExpenseApprovalMapper;
import com.g1.back_end.repository.VariableExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VariableExpenseService {

    @Autowired
    private VariableExpenseRepository variableExpenseRepository;

    @Autowired
    private VariableExpenseMapper variableExpenseMapper;

    @Autowired
    private VariableExpenseApprovalMapper variableExpenseApprovalMapper;

    public VariableExpenseDTO createVariableExpense(VariableExpenseDTO variableExpenseDTO, Employee responsible) {
        // Aqui, obtemos o centro de custo do funcionário (responsável) diretamente
        VariableExpense variableExpense = variableExpenseMapper.dtoToVariableExpense(variableExpenseDTO, responsible.getCenterCost(), responsible);
        variableExpense = variableExpenseRepository.save(variableExpense);
        return variableExpenseMapper.variableExpenseToDTO(variableExpense);
    }


    // Atualizar despesa variável existente
    public VariableExpenseDTO updateVariableExpense(Long id, VariableExpenseDTO variableExpenseDTO, Employee responsible) {
        Optional<VariableExpense> existingExpense = variableExpenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            VariableExpense variableExpense = existingExpense.get();
            variableExpense.setType(variableExpenseDTO.getType());
            variableExpense.setDescriber(variableExpenseDTO.getDescriber());
            variableExpense.setValue(variableExpenseDTO.getValue());
            variableExpense.setDate(variableExpenseDTO.getDate());
            variableExpense.setCategory(variableExpenseDTO.getCategory());
            variableExpense.setPaymentMethod(variableExpenseDTO.getPaymentMethod());
            variableExpense.setObservation(variableExpenseDTO.getObservation());
            variableExpense.setApproval(variableExpenseDTO.getApproval());
            variableExpense.setCostCenter(variableExpense.getCostCenter()); // Mantém o Centro de Custo
            variableExpense.setResponsibile(responsible); // Atualiza o responsável
            variableExpense = variableExpenseRepository.save(variableExpense);
            return variableExpenseMapper.variableExpenseToDTO(variableExpense);
        } else {
            throw new BadRequestException("Despesa variável não encontrada.");
        }
    }

    // Aprovar ou negar uma despesa variável
    public VariableExpenseApprovalDTO approveVariableExpense(Long id, VariableExpenseApprovalDTO approvalDTO, Employee responsible) {
        Optional<VariableExpense> existingExpense = variableExpenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            VariableExpense variableExpense = existingExpense.get();
            variableExpense.setApproval(approvalDTO.getApproved());
            variableExpense.setResponsibile(responsible); // Define o responsável pela aprovação
            variableExpense = variableExpenseRepository.save(variableExpense);
            return variableExpenseApprovalMapper.variableExpenseToApprovalDTO(variableExpense);
        } else {
            throw new BadRequestException("Despesa variável não encontrada.");
        }
    }

    public List<VariableExpenseDTO> getExpensesByCostCenter(Long costCenterId) {
        List<VariableExpense> expenses = variableExpenseRepository.findByCostCenterId(costCenterId);
        return expenses.stream()
                .map(VariableExpenseMapper::variableExpenseToDTO)  // Método estático chamado pela classe
                .toList();
    }


    public List<VariableExpenseDTO> getExpensesByResponsible(String responsibleName) {
        List<VariableExpense> expenses = variableExpenseRepository.findByResponsibileName(responsibleName); // Corrigido aqui
        return expenses.stream()
                .map(VariableExpenseMapper::variableExpenseToDTO)  // Usando o nome da classe diretamente
                .toList();
    }

    // Buscar uma despesa variável por data e responsável
    public VariableExpenseDTO getExpenseByDateAndResponsible(LocalDate date, String responsibleName) {
        Optional<VariableExpense> expense = variableExpenseRepository.findByDateAndResponsibile(date, responsibleName);
        if (expense.isPresent()) {
            return variableExpenseMapper.variableExpenseToDTO(expense.get());
        } else {
            throw new BadRequestException("Despesa variável não encontrada.");
        }
    }

    // Excluir despesa variável
    public void deleteVariableExpense(Long id) {
        if (variableExpenseRepository.existsById(id)) {
            variableExpenseRepository.deleteById(id);
        } else {
            throw new BadRequestException("Despesa variável não encontrada.");
        }
    }
}
