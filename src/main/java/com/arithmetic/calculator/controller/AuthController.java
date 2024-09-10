package com.arithmetic.calculator.controller;

import com.arithmetic.calculator.dto.request.UserRequestDTO;
import com.arithmetic.calculator.service.AuthService;
import com.arithmetic.calculator.service.impl.AuthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody UserRequestDTO authRequest) {
        try {
            String token = service.authenticate(authRequest);
            return ResponseEntity.ok().body(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
