package com.g1.back_end.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "centro_de_custos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArea;
    @NotBlank(message = "O nome não pode ser nulo")
    private String name;
    @NotNull
    private Integer budgetArea;
}