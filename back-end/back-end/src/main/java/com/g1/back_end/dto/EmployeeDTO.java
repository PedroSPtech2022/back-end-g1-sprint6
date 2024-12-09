package com.g1.back_end.dto;
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
    private String name;
    @NotNull(message = "email do funcionario não pode estar vazio")
    private String email;
    @NotNull(message = "cargo  não pode estar vazio")
    private String jobTitle;
    @NotNull(message = "posicao não pode estar vazio")
    private String position;
    @NotBlank
    private BigDecimal salary ;
}