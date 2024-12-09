package com.g1.back_end.dto;
import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    @NotNull(message = "nome do funcionario não pode estar vazio")
    private String nome;
    @NotNull(message = "email do funcionario não pode estar vazio")
    private String email;
    @NotNull(message = "cargo  não pode estar vazio")
    private String cargo;
    @NotNull(message = "posicao não pode estar vazio")
    private String senioridade;
    @NotBlank
    private Double salario;
    private Long centerCostId;
    private Long userId;
}