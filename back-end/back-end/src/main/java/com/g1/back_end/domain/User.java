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
    private Long idUser;
    @NotNull(message = "o nome não pode estar vazio")
    private String name;
    @Email
    private String email;
    @NotNull(message = "a senha pode estar vazio")
    private String password;
    @NotNull(message = "o tipo do usuario não pode estar vazio")
    private String typeUser;

    @ManyToOne
    @JoinColumn(name= "area_id", referencedColumnName="area_id"  )
    private Employee employee;
}
