package com.alkemy.paypocket.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    @NotBlank(message = "Campo currency OBLIGATORIO")
    private String currency;

    @NotNull(message = "Campo transactionLimit OBLIGATORIO")
    private Double transactionLimit;

    @NotNull(message = "Campo balance OBLIGATORIO")
    private Double balance;

    @NotNull(message = "Campo user_id OBLIGATORIO")
    private Integer user_id;
    
}



