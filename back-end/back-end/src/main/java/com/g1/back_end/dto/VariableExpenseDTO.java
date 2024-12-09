package com.g1.back_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariableExpenseDTO {
    @NotNull(message = "tipo não pode estar vazio")
    private String type;
    private String describer;
    @NotBlank
    private BigDecimal value;
    private LocalDate date;
    private String responsible;
    @NotNull(message = "categoria não pode estar vazio")
    private String category;
    @NotNull(message = "metodo de pagamento não pode estar vazio")
    private String paymentMethod;
    private String observation;
    private Boolean approval;
}
