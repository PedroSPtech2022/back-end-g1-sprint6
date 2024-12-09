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

    @NotBlank(message = "O responsável não pode ser nulo")
    private String responsible;  // Aqui você pode usar EmployeeDTO, se necessário

    @NotBlank
    private AreaDTO area;  // Mudando para AreaDTO para refletir a área do centro de custo

    @NotBlank(message = "O nome do centro de custo não pode ser nulo")
    private String name;

    @NotNull(message = "O orçamento anual não pode ser nulo")
    private Integer annualBudget;

    @NotBlank(message = "O tipo do centro de custo não pode ser nulo")
    private String typeCostCenter;
}
