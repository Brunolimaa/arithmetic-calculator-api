package com.arithmetic.calculator.service.impl;

import com.arithmetic.calculator.model.Operation;
import com.arithmetic.calculator.repository.OperationRepository;
import com.arithmetic.calculator.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Operation findById(Long id) {
        return operationRepository.findById(id).orElse(null);
    }

    @Override
    public Operation findByType(String type) {
        return operationRepository.findByType(type);
    }

}