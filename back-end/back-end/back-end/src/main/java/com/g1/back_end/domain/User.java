package com.g1.back_end.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    @NotNull(message = "o nome não pode estar vazio")
    private String nome;
    @Email
    private String email;
    @NotNull(message = "a senha pode estar vazio")
    private String senha;
    @NotNull(message = "o tipo do usuario não pode estar vazio")
    private String tipo_usuario;
}
