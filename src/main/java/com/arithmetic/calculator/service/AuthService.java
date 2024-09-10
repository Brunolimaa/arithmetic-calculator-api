package com.arithmetic.calculator.service;

import com.arithmetic.calculator.dto.request.UserRequestDTO;

public interface AuthService {

    String authenticate(UserRequestDTO userRequest);

}
