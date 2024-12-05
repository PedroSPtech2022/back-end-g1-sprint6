package com.g1.back_end.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CostCenterDTO {
    private String responsible;
    private String area;
    @NotBlank(message = "O nome do centro de custo não pode ser nulo")
    private String name;
    @NotNull
    private Integer annualBudget;
    @NotBlank(message = "O tipo do centro de custo não pode ser nulo")
    private String typeCostCenter;
}
