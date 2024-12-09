package com.g1.back_end.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariableExpenseApprovalDTO {
    private LocalDate date;
    private String responsible;
    @NotNull(message = "tripo não pode estar vazio")
    private String variableType;
    private Boolean approved;
}
