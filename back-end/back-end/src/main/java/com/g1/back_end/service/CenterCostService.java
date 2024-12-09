package com.g1.back_end.service;

import com.g1.back_end.domain.AreaDomain;
import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.dto.CostCenterDTO;
import com.g1.back_end.exception.BadRequestException;
import com.g1.back_end.mapper.CostCenterMapper;
import com.g1.back_end.repository.AreaRepository;
import com.g1.back_end.repository.CenterCostRepository;
import com.g1.back_end.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CenterCostService {

    @Autowired
    private CenterCostRepository centerCostRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Método para criar um centro de custo
    public CostCenterDTO createCenterCost(CostCenterDTO costCenterDTO) {
        // Buscar a área pelo nome, acessando o nome da área no DTO
        AreaDomain area = areaRepository.findByName(costCenterDTO.getArea().getName())
                .orElseThrow(() -> new BadRequestException("Área não encontrada: " + costCenterDTO.getArea().getName()));

        // Buscar o funcionário responsável pelo nome
        Employee responsible = employeeRepository.findByName(costCenterDTO.getResponsible())
                .orElseThrow(() -> new BadRequestException("Funcionário responsável não encontrado: " + costCenterDTO.getResponsible()));

        // Verificar se já existe um centro de custo com o mesmo nome e área
        if (centerCostRepository.existsByNameAndArea(costCenterDTO.getName(), area)) {
            throw new BadRequestException("Já existe um centro de custo com este nome na área especificada.");
        }

        // Converter o DTO para a entidade e salvar
        CenterCost centerCost = CostCenterMapper.dtoToCenterCost(costCenterDTO, area, responsible);
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

        AreaDomain area = areaRepository.findByName(costCenterDTO.getArea().getName())
                .orElseThrow(() -> new BadRequestException("Área não encontrada: " + costCenterDTO.getArea()));

        Employee responsible = employeeRepository.findByName(costCenterDTO.getResponsible())
                .orElseThrow(() -> new BadRequestException("Funcionário responsável não encontrado: " + costCenterDTO.getResponsible()));

        centerCost.setName(costCenterDTO.getName());
        centerCost.setTypeCostCenter(costCenterDTO.getTypeCostCenter());
        centerCost.setAnnualBudget(costCenterDTO.getAnnualBudget());
        centerCost.setArea(area);
        centerCost.setResponsible(responsible);

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
