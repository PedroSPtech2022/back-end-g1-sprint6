package com.g1.back_end.mapper;

import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.VariableExpense;
import com.g1.back_end.dto.VariableExpenseDTO;
import org.springframework.stereotype.Component;

@Component
public class VariableExpenseMapper {

    public static VariableExpense dtoToVariableExpense(
            VariableExpenseDTO variableExpenseDTO,
            CenterCost costCenter,
            Employee responsible
    ) {
        VariableExpense variableExpense = new VariableExpense();
        variableExpense.setType(variableExpenseDTO.getType());
        variableExpense.setDescriber(variableExpenseDTO.getDescriber());
        variableExpense.setValue(variableExpenseDTO.getValue());
        variableExpense.setDate(variableExpenseDTO.getDate());
        variableExpense.setCategory(variableExpenseDTO.getCategory());
        variableExpense.setPaymentMethod(variableExpenseDTO.getPaymentMethod());
        variableExpense.setObservation(variableExpenseDTO.getObservation());
        variableExpense.setApproval(variableExpenseDTO.getApproval());
        variableExpense.setCostCenter(costCenter); // Relaciona com o Centro de Custo
        variableExpense.setResponsibile(responsible); // Define o funcionário responsável
        return variableExpense;
    }

    public static VariableExpenseDTO variableExpenseToDTO(VariableExpense variableExpense) {
        VariableExpenseDTO variableExpenseDTO = new VariableExpenseDTO();
        variableExpenseDTO.setType(variableExpense.getType());
        variableExpenseDTO.setDescriber(variableExpense.getDescriber());
        variableExpenseDTO.setValue(variableExpense.getValue());
        variableExpenseDTO.setDate(variableExpense.getDate());
        variableExpenseDTO.setCategory(variableExpense.getCategory());
        variableExpenseDTO.setPaymentMethod(variableExpense.getPaymentMethod());
        variableExpenseDTO.setObservation(variableExpense.getObservation());
        variableExpenseDTO.setApproval(variableExpense.getApproval());
        variableExpenseDTO.setResponsible(variableExpense.getResponsibile() != null
                ? variableExpense.getResponsibile().getName()
                : null); // Inclui o nome do responsável, se disponível
        return variableExpenseDTO;
    }
}
