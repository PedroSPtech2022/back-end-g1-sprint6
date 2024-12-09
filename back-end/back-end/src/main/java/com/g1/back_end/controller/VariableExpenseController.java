package com.g1.back_end.controller;

import com.g1.back_end.domain.Employee;
import com.g1.back_end.dto.VariableExpenseDTO;
import com.g1.back_end.dto.VariableExpenseApprovalDTO;
import com.g1.back_end.service.VariableExpenseService;
import com.g1.back_end.exception.BadRequestException;
import com.g1.back_end.repository.EmployeeRepository;  // Importando o repositório de Employee
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/variable-expenses")
public class VariableExpenseController {

    @Autowired
    private VariableExpenseService variableExpenseService;

    @Autowired
    private EmployeeRepository employeeRepository;  // Injeção do repositório de Employee

    @PostMapping
    public ResponseEntity<VariableExpenseDTO> createVariableExpense(@RequestBody VariableExpenseDTO variableExpenseDTO,
                                                                    @RequestParam Long responsibleId) {
        // Buscando o funcionário responsável pelo ID
        Optional<Employee> responsibleOpt = employeeRepository.findById(responsibleId);

        if (responsibleOpt.isPresent()) {
            Employee responsible = responsibleOpt.get();

            try {
                VariableExpenseDTO createdExpense = variableExpenseService.createVariableExpense(variableExpenseDTO, responsible);
                return ResponseEntity.ok(createdExpense);
            } catch (BadRequestException e) {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.badRequest().body(null);  // Retorna erro se o responsável não for encontrado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VariableExpenseDTO> updateVariableExpense(@PathVariable Long id,
                                                                    @RequestBody VariableExpenseDTO variableExpenseDTO,
                                                                    @RequestParam Long responsibleId) {
        Optional<Employee> responsibleOpt = employeeRepository.findById(responsibleId);

        if (responsibleOpt.isPresent()) {
            Employee responsible = responsibleOpt.get();

            try {
                VariableExpenseDTO updatedExpense = variableExpenseService.updateVariableExpense(id, variableExpenseDTO, responsible);
                return ResponseEntity.ok(updatedExpense);
            } catch (BadRequestException e) {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.badRequest().body(null);  // Retorna erro se o responsável não for encontrado
        }
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<VariableExpenseApprovalDTO> approveVariableExpense(@PathVariable Long id,
                                                                             @RequestBody VariableExpenseApprovalDTO approvalDTO,
                                                                             @RequestParam Long responsibleId) {
        Optional<Employee> responsibleOpt = employeeRepository.findById(responsibleId);

        if (responsibleOpt.isPresent()) {
            Employee responsible = responsibleOpt.get();

            try {
                VariableExpenseApprovalDTO approvedExpense = variableExpenseService.approveVariableExpense(id, approvalDTO, responsible);
                return ResponseEntity.ok(approvedExpense);
            } catch (BadRequestException e) {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.badRequest().body(null);  // Retorna erro se o responsável não for encontrado
        }
    }

    @GetMapping("/cost-center/{costCenterId}")
    public ResponseEntity<List<VariableExpenseDTO>> getExpensesByCostCenter(@PathVariable Long costCenterId) {
        List<VariableExpenseDTO> expenses = variableExpenseService.getExpensesByCostCenter(costCenterId);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/responsible/{responsibleName}")
    public ResponseEntity<List<VariableExpenseDTO>> getExpensesByResponsible(@PathVariable String responsibleName) {
        List<VariableExpenseDTO> expenses = variableExpenseService.getExpensesByResponsible(responsibleName);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/date")
    public ResponseEntity<VariableExpenseDTO> getExpenseByDateAndResponsible(@RequestParam LocalDate date,
                                                                             @RequestParam String responsibleName) {
        try {
            VariableExpenseDTO expense = variableExpenseService.getExpenseByDateAndResponsible(date, responsibleName);
            return ResponseEntity.ok(expense);
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariableExpense(@PathVariable Long id) {
        try {
            variableExpenseService.deleteVariableExpense(id);
            return ResponseEntity.noContent().build();
        } catch (BadRequestException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
