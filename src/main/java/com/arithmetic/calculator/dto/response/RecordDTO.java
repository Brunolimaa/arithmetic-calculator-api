package com.arithmetic.calculator.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {

    private Long id;
    private OperationDTO operation;
    private UserDTO user;
    private double amount;
    private double userBalance;
    private String operationResponse;
    private LocalDateTime date;

}