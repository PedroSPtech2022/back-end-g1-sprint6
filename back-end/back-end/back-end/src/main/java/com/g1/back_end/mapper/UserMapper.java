package com.g1.back_end.mapper;

import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.User;
import com.g1.back_end.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User dtoToUser(UserDTO userDTO, Employee employee) {
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setEmail(userDTO.getEmail());
        user.setSenha(userDTO.getSenha());
        user.setTipo_usuario(userDTO.getTipo_usuario());
        return user;
    }

    public static UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNome(user.getNome());
        userDTO.setEmail(user.getEmail());
        userDTO.setTipo_usuario(user.getTipo_usuario());
        return userDTO;
    }
}
