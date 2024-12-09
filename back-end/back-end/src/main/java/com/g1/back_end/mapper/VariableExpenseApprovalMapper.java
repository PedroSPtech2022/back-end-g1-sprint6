package com.g1.back_end.mapper;

import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.VariableExpense;
import com.g1.back_end.dto.VariableExpenseApprovalDTO;
import org.springframework.stereotype.Component;

@Component
public class VariableExpenseApprovalMapper {

    public static VariableExpense dtoToVariableExpense(VariableExpenseApprovalDTO approvalDTO, Employee responsible) {
        VariableExpense variableExpense = new VariableExpense();
        variableExpense.setDate(approvalDTO.getDate());
        variableExpense.setType(approvalDTO.getVariableType());
        variableExpense.setApproval(approvalDTO.getApproved());
        variableExpense.setResponsibile(responsible); // Define o responsável, se fornecido
        return variableExpense;
    }

    public static VariableExpenseApprovalDTO variableExpenseToApprovalDTO(VariableExpense variableExpense) {
        VariableExpenseApprovalDTO approvalDTO = new VariableExpenseApprovalDTO();
        approvalDTO.setDate(variableExpense.getDate());
        approvalDTO.setVariableType(variableExpense.getType());
        approvalDTO.setApproved(variableExpense.getApproval());
        approvalDTO.setResponsible(variableExpense.getResponsibile() != null
                ? variableExpense.getResponsibile().getName()
                : null); // Inclui o nome do responsável, se disponível
        return approvalDTO;
    }
}
