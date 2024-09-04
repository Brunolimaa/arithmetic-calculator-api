package com.arithmetic.calculator.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OperationDTO {

    private Long id;
    private String type;
    private double cost;

}
