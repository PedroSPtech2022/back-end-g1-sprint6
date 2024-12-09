package com.g1.back_end.service;

import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.OrcamentoAnualDomain;
import com.g1.back_end.dto.CostCenterDTO;
import com.g1.back_end.exception.BadRequestException;
import com.g1.back_end.mapper.CostCenterMapper;
import com.g1.back_end.repository.AreaRepository;
import com.g1.back_end.repository.CenterCostRepository;
import com.g1.back_end.repository.OrcamentoAnualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CenterCostService {

    @Autowired
    private CenterCostRepository centerCostRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private OrcamentoAnualRepository orcamentoAnualRepository;

    // Método para criar um centro de custo
    public CostCenterDTO createCenterCost(CostCenterDTO costCenterDTO) {
        // Buscar a área pelo nome, acessando o nome da área no DTO
        AreaDomain area = areaRepository.findByArea(costCenterDTO.getArea())
                .orElseThrow(() -> new BadRequestException("Área não encontrada: " + costCenterDTO.getArea()));

        // Converter o DTO para a entidade e salvar
        CenterCost centerCost = CostCenterMapper.dtoToCenterCost(costCenterDTO);
        centerCost = centerCostRepository.save(centerCost);

        // Converter a entidade de volta para DTO e retornar
        return CostCenterMapper.costCenterDtoToDTO(centerCost);
    }

    public List<CostCenterDTO> getAllCenterCosts() {
        return centerCostRepository.findAll()
                .stream()
                .map(CostCenterMapper::costCenterDtoToDTO)
                .collect(Collectors.toList());
    }

    public CostCenterDTO getCenterCostById(Long id) {
        CenterCost centerCost = centerCostRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Centro de custo não encontrado com ID: " + id));
        return CostCenterMapper.costCenterDtoToDTO(centerCost);
    }

    public CostCenterDTO updateCenterCost(Long id, CostCenterDTO costCenterDTO) {
        CenterCost centerCost = centerCostRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Centro de custo não encontrado com ID: " + id));

        AreaDomain area = areaRepository.findByArea(costCenterDTO.getArea())
                .orElseThrow(() -> new BadRequestException("Área não encontrada: " + costCenterDTO.getArea()));

        centerCost.setNomeCentro(costCenterDTO.getNomeCentro());
        centerCost.setTipo(costCenterDTO.getTipo());
        Optional<OrcamentoAnualDomain> orcamento = orcamentoAnualRepository.findById(costCenterDTO.getFk_orcamento_anual());
        centerCost.setFk_orcamento_anual(orcamento);
        centerCost.setArea(area);

        centerCost = centerCostRepository.save(centerCost);
        return CostCenterMapper.costCenterDtoToDTO(centerCost);
    }

    public void deleteCenterCost(Long id) {
        if (!centerCostRepository.existsById(id)) {
            throw new BadRequestException("Centro de custo não encontrado com ID: " + id);
        }
        centerCostRepository.deleteById(id);
    }
}
