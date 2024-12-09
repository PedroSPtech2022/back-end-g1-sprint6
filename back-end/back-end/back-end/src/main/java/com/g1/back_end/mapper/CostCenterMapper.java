package com.g1.back_end.mapper;

import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.dto.CostCenterDTO;
import org.springframework.stereotype.Component;

@Component
public class CostCenterMapper {

    public static CenterCost dtoToCenterCost(CostCenterDTO costCenterDTO){
        CenterCost centerCost = new CenterCost();
        centerCost.setNomeCentro(costCenterDTO.getNomeCentro());
        centerCost.setTipo(costCenterDTO.getTipo());
        centerCost.setArea(costCenterDTO.getArea());
        centerCost.setFk_orcamento_anual(costCenterDTO.getFk_orcamento_anual());
        return centerCost;
    }

    public static CostCenterDTO costCenterDtoToDTO(CenterCost centerCost) {
        CostCenterDTO costCenterDTO = new CostCenterDTO();
        costCenterDTO.setNomeCentro(centerCost.getNomeCentro());
        costCenterDTO.setTipo(centerCost.getTipo());
        costCenterDTO.setFk_orcamento_anual(centerCost.getFk_orcamento_anual());
        costCenterDTO.setArea(centerCost.getArea());

        return costCenterDTO;
    }

}
