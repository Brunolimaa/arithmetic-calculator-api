package com.arithmetic.calculator.service;

import com.arithmetic.calculator.model.User;

public interface UserService {

    User findByUsername(String username);

}
