package com.g1.back_end.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gastos_variaveis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariableExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_gastos_variaveis")
    private Long idGastosVariaveis;
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
    @JoinColumn(name = "fk_centro_de_custos", nullable = false)
    private CenterCost centroDeCustos;


}
