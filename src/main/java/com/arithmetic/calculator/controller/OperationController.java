package com.arithmetic.calculator.controller;

import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.arithmetic.calculator.model.Record;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("api/v1/operations")
public class OperationController {
    @Autowired
    private CalculationService calculationService;

    @PostMapping("/{type}")
    public ResponseEntity<String> performOperation(@PathVariable("type") String type,
                                                  @RequestParam("number1") double n1,
                                                  @RequestParam("number2") double n2) {
        return ResponseEntity.ok(calculationService.performOperation(1L, type, n1, n2));
    }

    @GetMapping
    public ResponseEntity<Page<RecordDTO>> getAllOperations(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(calculationService.getAllOperations(pageable));
    }
}