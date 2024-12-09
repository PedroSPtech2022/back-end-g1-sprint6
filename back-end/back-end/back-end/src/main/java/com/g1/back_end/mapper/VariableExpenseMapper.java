package com.g1.back_end.mapper;

import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.VariableExpense;
import com.g1.back_end.dto.VariableExpenseDTO;
import org.springframework.stereotype.Component;

@Component
public class VariableExpenseMapper {

    public static VariableExpense dtoToVariableExpense(VariableExpenseDTO variableExpenseDTO, CenterCost costCenter, String responsible) {
        VariableExpense variableExpense = new VariableExpense();
        variableExpense.setTipo_variavel(variableExpenseDTO.getTipo_variavel());
        variableExpense.setDesc_transacao(variableExpenseDTO.getDesc_transacao());
        variableExpense.setValor(variableExpenseDTO.getValor());
        variableExpense.setData(variableExpenseDTO.getData());
        variableExpense.setCategoria_despesa(variableExpenseDTO.getCategoria_despesa());
        variableExpense.setMetodo_pagto(variableExpenseDTO.getMetodo_pagto());
        variableExpense.setObs(variableExpenseDTO.getObs());
        variableExpense.setAprovado(variableExpenseDTO.getAprovado());
        variableExpense.setCentroDeCustos(costCenter);
        variableExpense.setResponsavel(responsible);
        return variableExpense;
    }

    public static VariableExpenseDTO variableExpenseToDTO(VariableExpense variableExpense) {
        VariableExpenseDTO variableExpenseDTO = new VariableExpenseDTO();
        variableExpenseDTO.setTipo_variavel(variableExpense.getTipo_variavel());
        variableExpenseDTO.setDesc_transacao(variableExpense.getDesc_transacao());
        variableExpenseDTO.setValor(variableExpense.getValor());
        variableExpenseDTO.setData(variableExpense.getData());
        variableExpenseDTO.setCategoria_despesa(variableExpense.getCategoria_despesa());
        variableExpenseDTO.setMetodo_pagto(variableExpense.getMetodo_pagto());
        variableExpenseDTO.setObs(variableExpense.getObs());
        variableExpenseDTO.setAprovado(variableExpense.getAprovado());
        variableExpenseDTO.setResponsavel(variableExpense.getResponsavel() != null ? variableExpense.getResponsavel() : null); // Inclui o nome do responsável, se disponível
        return variableExpenseDTO;
    }
}
