package com.arithmetic.calculator.repository;

import com.arithmetic.calculator.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    Operation findByType(String type);
    
}