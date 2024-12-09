package com.g1.back_end.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull(message = "O nome não pode estar vazio")
    private String nome;
    @Email(message = "O e-mail deve ser válido")
    private String email;
    @NotNull(message = "A senha não pode estar vazio")
    private String senha;
    @NotNull(message = "O tipo do usuário não pode estar vazio")
    private String tipo_usuario;
}
