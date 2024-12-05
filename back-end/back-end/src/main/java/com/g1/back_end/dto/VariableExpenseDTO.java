package com.g1.back_end.dto;

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

    private String type;
    private String describe;
    private BigDecimal value;
    private LocalDate date;
    private String responsible;
    private String category;
    private String paymentMethod;
    private String observation;
    private Boolean approval;
}
