package com.g1.back_end.service;

import com.g1.back_end.domain.User;
import com.g1.back_end.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User login(String email, String senha) {
        return userRepository.findByEmailAndSenha(email, senha)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public User salvarUsuario(User usuario) {
        return userRepository.save(usuario);
    }
}