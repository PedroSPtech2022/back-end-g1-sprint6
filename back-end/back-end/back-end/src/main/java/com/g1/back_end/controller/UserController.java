package com.g1.back_end.controller;

import com.g1.back_end.dto.LoginRequest;
import com.g1.back_end.dto.UserDTO;
import com.g1.back_end.service.UserService;
import com.g1.back_end.domain.Employee;
import com.g1.back_end.exception.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para criar um novo usuário
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO, @RequestParam Long employeeId) {
        // Aqui você busca o funcionário pelo ID para associá-lo ao usuário
        Employee employee = new Employee();  // Busque o Employee no banco de dados
        // Adicione a lógica de buscar o Employee com o ID fornecido
        if (employee == null) {
            throw new BadRequestException("Funcionário não encontrado.");
        }

        UserDTO createdUser = userService.createUser(userDTO, employee);
        return ResponseEntity.status(201).body(createdUser);
    }

    // Endpoint para buscar um usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    // Endpoint para login (autenticação) de usuário
    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody LoginRequest loginRequest) {
        // Extrai email e senha do objeto LoginRequest
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UserDTO userDTO = userService.loginUser(email, password);
        return ResponseEntity.ok(userDTO);
    }

    // Endpoint para atualizar um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO, @RequestParam Long employeeId) {
        // Aqui você busca o funcionário pelo ID para associá-lo ao usuário
        Employee employee = new Employee();  // Busque o Employee no banco de dados
        // Adicione a lógica de buscar o Employee com o ID fornecido
        if (employee == null) {
            throw new BadRequestException("Funcionário não encontrado.");
        }

        UserDTO updatedUser = userService.updateUser(id, userDTO, employee);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint para excluir um usuário pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
