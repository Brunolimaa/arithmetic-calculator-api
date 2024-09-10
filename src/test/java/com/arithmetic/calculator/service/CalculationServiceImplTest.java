package com.arithmetic.calculator.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.model.Operation;
import com.arithmetic.calculator.model.User;
import com.arithmetic.calculator.model.Record;
import com.arithmetic.calculator.service.impl.CalculationServiceImpl;
import com.arithmetic.calculator.service.impl.OperationServiceImpl;
import com.arithmetic.calculator.service.impl.RecordServiceImpl;
import com.arithmetic.calculator.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CalculationServiceImplTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private OperationServiceImpl operationServiceImpl;

    @Mock
    private RecordServiceImpl recordServiceImpl;

    @InjectMocks
    private CalculationServiceImpl calculationServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void performOperation_UserNotFound() {
        when(userServiceImpl.findByUsername(anyString())).thenReturn(null);

        String result = calculationServiceImpl.performOperation("add", 1.0, 2.0);
        assertEquals("User not found", result);
    }

    @Test
    void performOperation_OperationNotFound() {
        User user = new User();
        user.setBalance(100.0);
        when(userServiceImpl.findByUsername(anyString())).thenReturn(user);
        when(operationServiceImpl.findByType(anyString())).thenReturn(null);

        String result = calculationServiceImpl.performOperation("add", 1.0, 2.0);
        assertEquals("Operation not found", result);
    }

    @Test
    void performOperation_InsufficientBalance() {
        User user = new User();
        user.setBalance(1.0);
        Operation operation = new Operation();
        operation.setCost(2.0);
        when(userServiceImpl.findByUsername(anyString())).thenReturn(user);
        when(operationServiceImpl.findByType(anyString())).thenReturn(operation);

        String result = calculationServiceImpl.performOperation("add", 1.0, 2.0);
        assertEquals("Insufficient balance", result);
    }

    @Test
    void performOperation_InvalidOperation() {
        User user = new User();
        user.setBalance(100.0);
        Operation operation = new Operation();
        operation.setCost(10.0);
        when(userServiceImpl.findByUsername(anyString())).thenReturn(user);
        when(operationServiceImpl.findByType(anyString())).thenReturn(operation);

        String result = calculationServiceImpl.performOperation("unknown", 1.0, 2.0);
        assertEquals("Invalid operation", result);
    }

    @Test
    void performOperation_Success() {
        User user = new User();
        user.setBalance(100.0);
        Operation operation = new Operation();
        operation.setCost(10.0);
        when(userServiceImpl.findByUsername(anyString())).thenReturn(user);
        when(operationServiceImpl.findByType(anyString())).thenReturn(operation);

        String result = calculationServiceImpl.performOperation( "addition", 1.0, 2.0);
        assertEquals("3.0", result);

        verify(recordServiceImpl).saveRecord(any(Record.class));
        assertEquals(90.0, user.getBalance());  // Check if balance is updated correctly
    }

    @Test
    void getAllOperations() {
        Pageable pageable = Pageable.ofSize(10);
        Page<RecordDTO> recordDTOPage = mock(Page.class);
        when(recordServiceImpl.getAllOperations(pageable)).thenReturn(recordDTOPage);

        Page<RecordDTO> result = calculationServiceImpl.getAllOperations(pageable);
        assertEquals(recordDTOPage, result);
    }
}