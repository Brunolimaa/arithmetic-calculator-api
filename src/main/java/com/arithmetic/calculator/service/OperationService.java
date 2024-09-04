package com.arithmetic.calculator.service;

import com.arithmetic.calculator.model.Operation;
import com.arithmetic.calculator.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    @Autowired
    private OperationRepository operationRepository;

    public Operation findById(Long id) {
        return operationRepository.findById(id).orElse(null);
    }

    public Operation findByType(String type) {
        return operationRepository.findByType(type);
    }

}