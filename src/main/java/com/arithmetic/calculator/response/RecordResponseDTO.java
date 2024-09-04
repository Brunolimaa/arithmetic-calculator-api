package com.arithmetic.calculator.response;

import org.springframework.lang.NonNull;
import lombok.*;

@Value
public class RecordResponseDTO {
    String operation;
    double amount;
    double balance;
    String response;
    String date;

    private RecordResponseDTO(
            @NonNull String operation,
            double amount,
            double balance,
            @NonNull String response,
            @NonNull String date) {
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
        this.response = response;
        this.date = date;
    }

}
