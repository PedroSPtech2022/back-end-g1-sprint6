package com.g1.back_end.mapper;

import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.VariableExpense;
import com.g1.back_end.dto.VariableExpenseApprovalDTO;
import org.springframework.stereotype.Component;

@Component
public class VariableExpenseApprovalMapper {

    public static VariableExpense dtoToVariableExpense(VariableExpenseApprovalDTO approvalDTO, Employee responsible) {
        VariableExpense variableExpense = new VariableExpense();
        variableExpense.setTipo_variavel(approvalDTO.getTipo_variavel());
        variableExpense.setAprovado(approvalDTO.getAprovado());
        variableExpense.setData(approvalDTO.getData());
        variableExpense.setResponsavel(approvalDTO.getResponsible());
        return variableExpense;
    }

    public static VariableExpenseApprovalDTO variableExpenseToApprovalDTO(VariableExpense variableExpense) {
        VariableExpenseApprovalDTO approvalDTO = new VariableExpenseApprovalDTO();
        approvalDTO.setData(variableExpense.getData());
        approvalDTO.setTipo_variavel(variableExpense.getTipo_variavel());
        approvalDTO.setAprovado(variableExpense.getAprovado());
        approvalDTO.setResponsible(variableExpense.getResponsavel() != null ? variableExpense.getResponsavel() : null);
        return approvalDTO;
    }
}
