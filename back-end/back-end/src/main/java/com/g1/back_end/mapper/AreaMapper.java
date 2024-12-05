package com.g1.back_end.mapper;


import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.dto.AreaDTO;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {

    public static AreaDomain dtoToArea(AreaDTO areaDTO) {
        AreaDomain area = new AreaDomain();
        area.setName(areaDTO.getName());
        area.setBudgetArea(areaDTO.getBudgetArea());
;       return area;
    }

    public static AreaDTO areaToDTO(AreaDomain area) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setName(area.getName());
        areaDTO.setBudgetArea(area.getBudgetArea());
        return areaDTO;
    }
}
