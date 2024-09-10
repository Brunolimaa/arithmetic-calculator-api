package com.arithmetic.calculator.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.arithmetic.calculator.dto.request.UserRequestDTO;
import com.arithmetic.calculator.model.User;
import com.arithmetic.calculator.repository.UserRepository;
import com.arithmetic.calculator.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void authenticate_UserNotFound_ThrowsException() {
        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setUsername("testuser");
        userRequest.setPassword("password");

        when(userRepository.findByUsername(anyString())).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.authenticate(userRequest);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    void authenticate_InvalidPassword_ThrowsException() {
        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setUsername("testuser");
        userRequest.setPassword("wrongpassword");

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("hashedpassword");

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.authenticate(userRequest);
        });

        assertEquals("Senha inválida", exception.getMessage());
    }

    @Test
    void authenticate_ValidCredentials_ReturnsToken() {
        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setUsername("testuser");
        userRequest.setPassword("password");

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("hashedpassword");

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtUtil.generateToken(anyString())).thenReturn("mockedToken");

        String token = authService.authenticate(userRequest);

        assertEquals("mockedToken", token);
    }
}