package com.alkemy.paypocket.dtos;

import com.alkemy.paypocket.entities.User;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    
    @NotBlank(message = "Campo currency OBLIGATORIO ")
    private String currency;

    @NotBlank(message = "Campo transactionLimit OBLIGATORIO ")
    private double transactionLimit;

    @NotBlank(message = "Campo balance OBLIGATORIO ")
    private double balance;

    @NotBlank(message = "Campo user_id OBLIGATORIO ")
    private User user_id;
    
}


