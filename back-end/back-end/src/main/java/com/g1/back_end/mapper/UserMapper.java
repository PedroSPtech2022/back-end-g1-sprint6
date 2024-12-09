package com.g1.back_end.mapper;

import com.g1.back_end.domain.Employee;
import com.g1.back_end.domain.User;
import com.g1.back_end.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User dtoToUser(UserDTO userDTO, Employee employee) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setTypeUser(userDTO.getTypeUser());
        user.setEmployee(employee);
        return user;
    }

    public static UserDTO userToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setTypeUser(user.getTypeUser());
        userDTO.setEmployeeName(user.getEmployee() != null ? user.getEmployee().getName() : null);
        return userDTO;
    }
}
