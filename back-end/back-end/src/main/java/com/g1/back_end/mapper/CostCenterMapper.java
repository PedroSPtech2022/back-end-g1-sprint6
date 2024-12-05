package com.g1.back_end.mapper;


import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
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

    public static CostCenterDTO costCenterDtoToDTO(CenterCost centerCost){
        CostCenterDTO costCenterDTO = new CostCenterDTO();
        costCenterDTO.setName(centerCost.getName());
        costCenterDTO.setTypeCostCenter(centerCost.getTypeCostCenter());
        costCenterDTO.setAnnualBudget(centerCost.getAnnualBudget());
        costCenterDTO.setArea(centerCost.getArea() != null ? centerCost.getArea().getName() : null);
        costCenterDTO.setResponsible(centerCost.getResponsible() != null ? centerCost.getResponsible().getName() : null);
        return costCenterDTO;
    }
}
