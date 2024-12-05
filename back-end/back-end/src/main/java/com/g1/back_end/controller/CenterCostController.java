package com.g1.back_end.controller;

import com.g1.back_end.domain.CenterCost;
import com.g1.back_end.dto.*;
import com.g1.back_end.service.CenterCostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/center-cost")
public class CenterCostController {

    @Autowired
    private CenterCostService costService;

    // POST /api/v1/cost-centers
    @PostMapping("/cost-centers")
    public ResponseEntity<Map<String,String>> createCostCenter(@Valid @RequestBody CostCenterDTO costCenterDTO) {
        CenterCost centerCost = costService.criarCenterCost(costCenterDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message","Centro de custos cadastrado com sucesso");
        return ResponseEntity.status(201).body(response);
    }

    // POST /api/v1/create-variable-cost/{id-cost-center}
    @PostMapping("/create-variable-cost/{id-cost-center}")
    public ResponseEntity<Void> addVariableExpense(@PathVariable("id-cost-center") Long costCenterId, @RequestBody VariableExpenseDTO variableExpenseDTO) {
        costService.addVariableExpense(costCenterId, variableExpenseDTO);
        return ResponseEntity.status(201).build(); // Created status
    }

    // GET /api/v1/variable-cost/by-cost-center/{id-cost-center}
     @GetMapping("/variable-cost/by-cost-center/{id-cost-center}")
    public ResponseEntity<List<VariableExpenseDTO>> getVariableExpensesByCostCenter(@PathVariable("id-cost-center") Long costCenterId) {
        List<VariableExpenseDTO> expenses = costService.getVariableExpensesByCostCenter(costCenterId);
        return ResponseEntity.ok(expenses);
    }

    // GET /api/v1/variable-cost/by-cost-center/{name-employee}
    @GetMapping("/variable-cost/by-cost-center/{name-employee}")
    public ResponseEntity<List<VariableExpenseDTO>> getVariableExpensesByEmployee(@PathVariable("name-employee") String employeeName) {
        List<VariableExpenseDTO> expenses = costService.getVariableExpensesByEmployee(employeeName);
        return ResponseEntity.ok(expenses);
    }
}

// PATCH /api/v1/create-variable-cost
//@PatchMapping("/create-variable-cost")
//public ResponseEntity<Void> approvalVariableExpense(@RequestBody VariableExpenseApprovalDTO approvalDTO) {
//   costService.approveVariableExpense(approvalDTO);
//   return ResponseEntity.ok().build();
//}
// GET /api/v1/cost-center/{id-executive}
// @GetMapping("/cost-center/{id-executive}")
//public ResponseEntity<CostCenterDTO> getCostCenterById(@PathVariable("id-executive") Long executiveId) {
//   CostCenterDTO costCenter = costService.getCostCenterById(executiveId);
//    return ResponseEntity.ok(costCenter);
//}

// GET /api/v1/employees/by-cost-center/{id-cost-center}
// @GetMapping("/employees/by-cost-center/{id-cost-center}")
//public ResponseEntity<List<EmployeeDTO>> getEmployeesByCostCenter(@PathVariable("id-cost-center") Long costCenterId) {
//  List<EmployeeDTO> employees = costService.getEmployeesByCostCenter(costCenterId);
//   return ResponseEntity.ok(employees);
//}