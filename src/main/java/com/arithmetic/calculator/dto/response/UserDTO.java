package com.arithmetic.calculator.dto.response;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @Email
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String status;

    @NotNull
    private Double balance;

}