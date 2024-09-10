package com.arithmetic.calculator.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "operations")
@Getter
@Setter
public class Operation {
    @Id
    private Long id;

    @NotEmpty
    private String type; // addition, subtraction, multiplication, division, square_root, random_string

    private double cost;

}
