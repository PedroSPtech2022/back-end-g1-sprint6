package com.g1.back_end.mapper;


import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.dto.AreaDTO;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper {

    public static AreaDomain dtoToArea(AreaDTO areaDTO) {
        AreaDomain area = new AreaDomain();
        area.setNomeArea(areaDTO.getNomeArea());
;       return area;
    }

    public static AreaDTO areaToDTO(AreaDomain area) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setNomeArea(area.getNomeArea());
        return areaDTO;
    }
}
