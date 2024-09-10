package com.arithmetic.calculator.controller;

import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.service.CalculationService;
import com.arithmetic.calculator.service.impl.CalculationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("api/v1/operations")
public class OperationController {

    @Autowired
    private CalculationService service;

    @PostMapping("/{type}")
    public ResponseEntity<String> performOperation(@PathVariable("type") String type,
                                                  @RequestParam("number1") double n1,
                                                  @RequestParam("number2") double n2) {
        return ResponseEntity.ok(service.performOperation(type, n1, n2));
    }

    @GetMapping
    public ResponseEntity<Page<RecordDTO>> getAllOperations(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAllOperations(pageable));
    }
}