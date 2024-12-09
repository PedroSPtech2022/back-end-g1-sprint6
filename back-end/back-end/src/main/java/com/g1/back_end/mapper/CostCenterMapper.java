package com.g1.back_end.mapper;


import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.dto.AreaDTO;
import com.g1.back_end.dto.CostCenterDTO;
import org.springframework.stereotype.Component;

@Component
public class CostCenterMapper {

    public static CenterCost dtoToCenterCost(CostCenterDTO costCenterDTO, AreaDomain area, Employee responsible){
        CenterCost centerCost = new CenterCost();
        centerCost.setName(costCenterDTO.getName());
        centerCost.setTypeCostCenter(costCenterDTO.getTypeCostCenter());
        centerCost.setAnnualBudget(costCenterDTO.getAnnualBudget());
        centerCost.setResponsible(responsible);
        centerCost.setArea(area);
        return centerCost;
    }

    public static CostCenterDTO costCenterDtoToDTO(CenterCost centerCost) {
        CostCenterDTO costCenterDTO = new CostCenterDTO();
        costCenterDTO.setName(centerCost.getName());
        costCenterDTO.setTypeCostCenter(centerCost.getTypeCostCenter());
        costCenterDTO.setAnnualBudget(centerCost.getAnnualBudget());

        // Acessando corretamente o nome da área e criando o AreaDTO
        if (centerCost.getArea() != null) {
            AreaDTO areaDTO = new AreaDTO();
            areaDTO.setName(centerCost.getArea().getName()); // Acesso ao nome da área
            areaDTO.setBudgetArea(centerCost.getArea().getBudgetArea()); // Acesso ao orçamento
            costCenterDTO.setArea(areaDTO); // Setando o AreaDTO no CostCenterDTO
        }

        // Acessando o nome do responsável
        if (centerCost.getResponsible() != null) {
            costCenterDTO.setResponsible(centerCost.getResponsible().getName());
        }

        return costCenterDTO;
    }

}
