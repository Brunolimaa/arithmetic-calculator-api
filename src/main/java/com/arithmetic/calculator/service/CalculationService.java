package com.arithmetic.calculator.service;

import com.arithmetic.calculator.dto.response.RecordDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CalculationService {

    String performOperation(String operationType, double n1, double n2);

    Page<RecordDTO> getAllOperations(Pageable pageable);
}
