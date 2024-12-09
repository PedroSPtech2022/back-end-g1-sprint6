package com.g1.back_end.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orcamento_anual")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrcamentoAnualDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_orcamento_anual;

    private LocalDateTime data_inicio;
    private LocalDateTime data_fim;
    @NotNull
    private Double orcamento_anual;
}
