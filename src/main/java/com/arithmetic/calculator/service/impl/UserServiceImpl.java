package com.arithmetic.calculator.service.impl;

import com.arithmetic.calculator.model.User;
import com.arithmetic.calculator.repository.UserRepository;
import com.arithmetic.calculator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}