package com.g1.back_end.dto;

import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.domain.OrcamentoAnualDomain;
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

    @NotBlank(message = "O nome do centro de custo não pode ser nulo")
    private String nomeCentro;
    @NotBlank(message = "O tipo do centro de custo não pode ser nulo")
    private String tipo;
    @NotNull(message = "O orçamento anual não pode ser nulo")
    private Long fk_orcamento_anual;;
    @NotBlank
    private Long area;
}
