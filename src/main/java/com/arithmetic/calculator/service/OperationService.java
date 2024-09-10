package com.arithmetic.calculator.service;

import com.arithmetic.calculator.model.Operation;

public interface OperationService {
    Operation findById(Long id);

    Operation findByType(String type);
}
