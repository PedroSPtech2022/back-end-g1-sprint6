package com.g1.back_end.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long idUser;

    @NotNull(message = "O nome não pode estar vazio")
    private String name;
    @Email(message = "O e-mail deve ser válido")
    private String email;
    @NotNull(message = "O tipo do usuário não pode estar vazio")
    private String typeUser;
    @NotNull(message = "A senha não pode estar vazio")
    private String password;

    private String employeeName; // Nome do funcionário relacionado, opcional
}
