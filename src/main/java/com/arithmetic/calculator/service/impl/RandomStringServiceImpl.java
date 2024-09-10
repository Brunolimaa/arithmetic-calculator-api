package com.arithmetic.calculator.service.impl;

import com.arithmetic.calculator.repository.RandomStringRepository;
import com.arithmetic.calculator.service.RandomStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomStringServiceImpl implements RandomStringService {

    @Autowired
    private RandomStringRepository repository;

    @Override
    public String getRandomString() {
        return repository.getRandomString();
    }
}