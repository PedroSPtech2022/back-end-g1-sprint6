package com.g1.back_end.dto;

import com.g1.back_end.domain.CenterCost;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariableExpenseDTO {
    private String tipo_variavel;
    @NotBlank
    private Double valor;
    @NotNull(message = "a categoria não pode estar vazio")
    private String categoria_despesa;
    private String desc_transacao;
    @NotNull(message = "o metodo de pagamento não pode estar vazio")
    private String metodo_pagto;
    private String obs;
    private LocalDateTime data;
    @NotNull(message = "o responsavel não pode estar vazio")
    private String responsavel;
    @NotNull(message = "nome do funcionario não pode estar vazio")
    private Boolean aprovado;
    @ManyToOne
    private CenterCost fk_centro_de_custos;

}
