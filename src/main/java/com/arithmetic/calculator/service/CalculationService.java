package com.arithmetic.calculator.service;

import com.arithmetic.calculator.dto.response.RecordDTO;
import com.arithmetic.calculator.model.Operation;
import com.arithmetic.calculator.model.User;
import com.arithmetic.calculator.model.Record;
import com.arithmetic.calculator.utils.OperationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {
    @Autowired
    private UserService userService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private RandomStringService randomStringService;

    public String performOperation(String operationType, double n1, double n2) {
        User user = userService.findByUsername("test@example.com"); // For simplicity, assuming a single user
        if (user == null) return "User not found";

        Operation operation = operationService.findByType(operationType); // Fetch operation details
        if (operation == null) return "Operation not found";

        double cost = operation.getCost();
        if (user.getBalance() < cost) return "Insufficient balance";
        String result = "";
        if(operationType.equals("random_string")) {
            result = randomStringService.getRandomString();
        } else {
            result = OperationUtil.getResultFromOperation(operationType, n1, n2);
        }

        if (result == null) return "Invalid operation";

        // Update user balance and save record
        user.setBalance(user.getBalance() - cost);
        recordService.saveRecord(new Record(operation, user, cost, user.getBalance(), result));

        return result;
    }

    public Page<RecordDTO> getAllOperations(Pageable pageable) {
        return recordService.getAllOperations(pageable);
    }
}