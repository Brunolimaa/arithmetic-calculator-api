package com.arithmetic.calculator.service.impl;

import com.arithmetic.calculator.dto.request.UserRequestDTO;
import com.arithmetic.calculator.model.User;
import com.arithmetic.calculator.repository.UserRepository;
import com.arithmetic.calculator.security.JwtUtil;
import com.arithmetic.calculator.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String authenticate(UserRequestDTO userRequest) {
        // Verify if user exist
        User user = userRepository.findByUsername(userRequest.getUsername());
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        // Verify the password is correct
        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        // Creat and return the token if the credentials is valids
        return jwtUtil.generateToken(userRequest.getUsername());
    }
}