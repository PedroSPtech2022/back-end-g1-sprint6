package com.g1.back_end.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;
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

    @ManyToOne
    @JoinColumn(name = "fk_centro_de_custos")
    private CenterCost centerCost;

}
