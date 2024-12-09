package com.g1.back_end.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Entity
@Table(name = "centro_de_custos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CenterCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_centro_de_custos")
    private Long idCentroDeCustos;
    @NotBlank(message = "O nome do centro de custo não pode ser nulo")
    @Column(name="nome_centro")
    private String nomeCentro;
    @NotBlank(message = "O tipo não pode ser nulo")
    private String tipo;

    @ManyToOne
    @JoinColumn(name="fk_orcamento_anual")
    private Optional<OrcamentoAnualDomain> fk_orcamento_anual;

    @ManyToOne
    @JoinColumn(name="fk_area")
    private AreaDomain area;
}
