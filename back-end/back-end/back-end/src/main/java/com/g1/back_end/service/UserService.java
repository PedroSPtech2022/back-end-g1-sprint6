package com.g1.back_end.service;

import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.User;
import com.g1.back_end.dto.UserDTO;
import com.g1.back_end.exception.BadRequestException;
import com.g1.back_end.mapper.UserMapper;
import com.g1.back_end.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    /**
     * Creates a new user and associates it with the given employee.
     *
     * @param userDTO   the user data to be created.
     * @param employee  the employee to associate with the user.
     * @return the created user as a DTO.
     */
    public UserDTO createUser(UserDTO userDTO, Employee employee) {
        // Verifica se o email já existe
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new BadRequestException("O email já está em uso.");
        }

        // Converte o DTO para entidade User
        User user = UserMapper.dtoToUser(userDTO, employee);

        // Salva o usuário no banco de dados
        User savedUser = userRepository.save(user);

        // Retorna o DTO do usuário criado
        return UserMapper.userToDTO(savedUser);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId the ID of the user.
     * @return the user data as a DTO.
     */
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado."));

        return UserMapper.userToDTO(user);
    }

    /**
     * Retrieves a user by email and password.
     *
     * @param email    the email of the user.
     * @param password the password of the user.
     * @return the user data as a DTO.
     */
    public UserDTO loginUser(String email, String password) {
        User user = userRepository.findByEmailAndSenha(email, password)
                .orElseThrow(() -> new BadRequestException("Email ou senha inválidos."));

        return UserMapper.userToDTO(user);
    }

    /**
     * Updates an existing user's details.
     *
     * @param userId   the ID of the user to be updated.
     * @param userDTO  the new user data.
     * @param employee the employee to associate with the user.
     * @return the updated user as a DTO.
     */
    public UserDTO updateUser(Long userId, UserDTO userDTO, Employee employee) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado."));

        // Atualiza os dados do usuário
        existingUser.setNome(userDTO.getNome());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setSenha(userDTO.getSenha());
        existingUser.setTipo_usuario(userDTO.getTipo_usuario());

        // Salva as alterações no banco de dados
        User updatedUser = userRepository.save(existingUser);

        return UserMapper.userToDTO(updatedUser);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete.
     */
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("Usuário não encontrado."));

        userRepository.delete(user);
    }

    /**
     * Checks if a user with the given email already exists.
     *
     * @param email the email to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
