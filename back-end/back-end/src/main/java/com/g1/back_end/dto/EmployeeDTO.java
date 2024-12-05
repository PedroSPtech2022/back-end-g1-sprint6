package com.g1.back_end.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    @NotNull(message = "nome do funcionario não pode estar vazio")
    private String name;
    @NotNull(message = "nome do funcionario não pode estar vazio")
    private String email;
    @NotNull(message = "nome do cargo não pode estar vazio")
    private String jobTitle;
    @NotNull(message = "nome da posicao não pode estar vazio")
    private String position;
    @NotBlank
    private BigDecimal salary ;
}