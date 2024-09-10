package com.arithmetic.calculator.service;

import com.arithmetic.calculator.repository.RandomStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomStringService {

    @Autowired
    private RandomStringRepository repository;

    public String getRandomString() {
        return repository.getRandomString();
    }
}