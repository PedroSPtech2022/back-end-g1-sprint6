package com.g1.back_end.controller;

import com.g1.back_end.dto.CostCenterDTO;
import com.g1.back_end.service.CenterCostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cost-centers")
public class CenterCostController {

    @Autowired
    private CenterCostService centerCostService;

    /**
     * Creates a new cost center.
     *
     * @param costCenterDTO the data for the new cost center.
     * @return the created cost center.
     */
    @PostMapping
    public ResponseEntity<CostCenterDTO> createCostCenter(@Valid @RequestBody CostCenterDTO costCenterDTO) {
        CostCenterDTO createdCostCenter = centerCostService.createCenterCost(costCenterDTO);
        return ResponseEntity.status(201).body(createdCostCenter);
    }

    /**
     * Retrieves all cost centers.
     *
     * @return a list of all cost centers.
     */
    @GetMapping
    public ResponseEntity<List<CostCenterDTO>> getAllCostCenters() {
        List<CostCenterDTO> costCenters = centerCostService.getAllCenterCosts();
        return ResponseEntity.ok(costCenters);
    }

    /**
     * Retrieves a specific cost center by ID.
     *
     * @param id the ID of the cost center to retrieve.
     * @return the requested cost center.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CostCenterDTO> getCostCenterById(@PathVariable Long id) {
        CostCenterDTO costCenter = centerCostService.getCenterCostById(id);
        return ResponseEntity.ok(costCenter);
    }

    /**
     * Updates a specific cost center by ID.
     *
     * @param id             the ID of the cost center to update.
     * @param costCenterDTO  the updated data for the cost center.
     * @return the updated cost center.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CostCenterDTO> updateCostCenter(@PathVariable Long id, @Valid @RequestBody CostCenterDTO costCenterDTO) {
        CostCenterDTO updatedCostCenter = centerCostService.updateCenterCost(id, costCenterDTO);
        return ResponseEntity.ok(updatedCostCenter);
    }

    /**
     * Deletes a specific cost center by ID.
     *
     * @param id the ID of the cost center to delete.
     * @return a response indicating the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostCenter(@PathVariable Long id) {
        centerCostService.deleteCenterCost(id);
        return ResponseEntity.noContent().build();
    }
}
