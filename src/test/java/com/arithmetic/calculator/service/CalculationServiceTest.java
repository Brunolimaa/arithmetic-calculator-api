package com.arithmetic.calculator.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.model.Operation;
import com.arithmetic.calculator.model.User;
import com.arithmetic.calculator.model.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CalculationServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private OperationService operationService;

    @Mock
    private RecordService recordService;

    @InjectMocks
    private CalculationService calculationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void performOperation_UserNotFound() {
        when(userService.findByUsername(anyString())).thenReturn(null);

        String result = calculationService.performOperation("add", 1.0, 2.0);
        assertEquals("User not found", result);
    }

    @Test
    void performOperation_OperationNotFound() {
        User user = new User();
        user.setBalance(100.0);
        when(userService.findByUsername(anyString())).thenReturn(user);
        when(operationService.findByType(anyString())).thenReturn(null);

        String result = calculationService.performOperation("add", 1.0, 2.0);
        assertEquals("Operation not found", result);
    }

    @Test
    void performOperation_InsufficientBalance() {
        User user = new User();
        user.setBalance(1.0);
        Operation operation = new Operation();
        operation.setCost(2.0);
        when(userService.findByUsername(anyString())).thenReturn(user);
        when(operationService.findByType(anyString())).thenReturn(operation);

        String result = calculationService.performOperation("add", 1.0, 2.0);
        assertEquals("Insufficient balance", result);
    }

    @Test
    void performOperation_InvalidOperation() {
        User user = new User();
        user.setBalance(100.0);
        Operation operation = new Operation();
        operation.setCost(10.0);
        when(userService.findByUsername(anyString())).thenReturn(user);
        when(operationService.findByType(anyString())).thenReturn(operation);

        String result = calculationService.performOperation("unknown", 1.0, 2.0);
        assertEquals("Invalid operation", result);
    }

    @Test
    void performOperation_Success() {
        User user = new User();
        user.setBalance(100.0);
        Operation operation = new Operation();
        operation.setCost(10.0);
        when(userService.findByUsername(anyString())).thenReturn(user);
        when(operationService.findByType(anyString())).thenReturn(operation);

        String result = calculationService.performOperation( "addition", 1.0, 2.0);
        assertEquals("3.0", result);

        verify(recordService).saveRecord(any(Record.class));
        assertEquals(90.0, user.getBalance());  // Check if balance is updated correctly
    }

    @Test
    void getAllOperations() {
        Pageable pageable = Pageable.ofSize(10);
        Page<RecordDTO> recordDTOPage = mock(Page.class);
        when(recordService.getAllOperations(pageable)).thenReturn(recordDTOPage);

        Page<RecordDTO> result = calculationService.getAllOperations(pageable);
        assertEquals(recordDTOPage, result);
    }
}