package com.arithmetic.calculator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "records")
@Getter
@Setter
@NoArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operation_id", nullable = false)
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double amount;
    private double userBalance;
    private String operationResponse;

    @Column(name = "date")
    private LocalDateTime date;


    public Record(Operation operation, User user, double cost, Double balance, String operationResponse) {

        this.operation = operation;
        this.user = user;
        this.amount = cost;
        this.userBalance = balance;
        this.operationResponse = operationResponse;
        this.date = LocalDateTime.now();
    }

    // Getters and setters
}