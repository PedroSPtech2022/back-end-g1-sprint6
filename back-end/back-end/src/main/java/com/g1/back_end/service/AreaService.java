package com.g1.back_end.service;

import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.dto.AreaDTO;
import com.g1.back_end.mapper.AreaMapper;
import com.g1.back_end.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public AreaDomain save(AreaDTO areaDTO) {
        AreaDomain area = AreaMapper.dtoToArea(areaDTO);
        return areaRepository.save(area);
    }

    public Optional<AreaDomain> findById(Long id) {
        return areaRepository.findById(id);
    }

    public List<AreaDomain> findAll() {
        return areaRepository.findAll();
    }

    public void deleteById(Long id) {
        areaRepository.deleteById(id);
    }
}
