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

    public VariableExpenseDTO createVariableExpense(VariableExpenseDTO variableExpenseDTO, String responsible) {
        // Aqui, obtemos o centro de custo do funcionário (responsável) diretamente
        VariableExpense variableExpense = variableExpenseMapper.dtoToVariableExpense(variableExpenseDTO,variableExpenseDTO.getFk_centro_de_custos(), responsible);
        variableExpense = variableExpenseRepository.save(variableExpense);
        return variableExpenseMapper.variableExpenseToDTO(variableExpense);
    }


    // Atualizar despesa variável existente
    public VariableExpenseDTO updateVariableExpense(Long id, VariableExpenseDTO variableExpenseDTO, String responsible) {
        Optional<VariableExpense> existingExpense = variableExpenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            VariableExpense variableExpense = existingExpense.get();
            variableExpense.setTipo_variavel(variableExpenseDTO.getTipo_variavel());
            variableExpense.setDesc_transacao(variableExpenseDTO.getDesc_transacao());
            variableExpense.setValor(variableExpenseDTO.getValor());
            variableExpense.setData(variableExpenseDTO.getData());
            variableExpense.setCategoria_despesa(variableExpenseDTO.getCategoria_despesa());
            variableExpense.setMetodo_pagto(variableExpenseDTO.getMetodo_pagto());
            variableExpense.setObs(variableExpenseDTO.getObs());
            variableExpense.setAprovado(variableExpenseDTO.getAprovado());
            variableExpense.setCentroDeCustos(variableExpense.getCentroDeCustos()); // Mantém o Centro de Custo
            variableExpense.setResponsavel(responsible); // Atualiza o responsável
            variableExpense = variableExpenseRepository.save(variableExpense);
            return variableExpenseMapper.variableExpenseToDTO(variableExpense);
        } else {
            throw new BadRequestException("Despesa variável não encontrada.");
        }
    }

    // Aprovar ou negar uma despesa variável
    public VariableExpenseApprovalDTO approveVariableExpense(Long id, VariableExpenseApprovalDTO approvalDTO, String responsible) {
        Optional<VariableExpense> existingExpense = variableExpenseRepository.findById(id);
        if (existingExpense.isPresent()) {
            VariableExpense variableExpense = existingExpense.get();
            variableExpense.setAprovado(approvalDTO.getAprovado());
            variableExpense.setResponsavel(responsible); // Define o responsável pela aprovação
            variableExpense = variableExpenseRepository.save(variableExpense);
            return variableExpenseApprovalMapper.variableExpenseToApprovalDTO(variableExpense);
        } else {
            throw new BadRequestException("Despesa variável não encontrada.");
        }
    }


    public List<VariableExpenseDTO> getExpensesByResponsible(String responsibleName) {
        List<VariableExpense> expenses = variableExpenseRepository.findByResponsavel(responsibleName); // Corrigido aqui
        return expenses.stream()
                .map(VariableExpenseMapper::variableExpenseToDTO)  // Usando o nome da classe diretamente
                .toList();
    }

    // Buscar uma despesa variável por data e responsável
    public VariableExpenseDTO getExpenseByDateAndResponsible(LocalDate date, String responsibleName) {
        Optional<VariableExpense> expense = variableExpenseRepository.findByDataAndResponsavel(date, responsibleName);
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
