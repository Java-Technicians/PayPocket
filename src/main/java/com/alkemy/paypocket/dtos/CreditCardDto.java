package com.alkemy.paypocket.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {

    @NotBlank(message = "Campo name OBLIGATORIO")
    private String name;

    @NotNull(message = "Campo amountAvailable OBLIGATORIO")
    private Double amountAvailable;

    @NotNull(message = "Campo amount OBLIGATORIO")
    private Double amount;

    @NotNull(message = "Campo account OBLIGATORIO")
    private  Integer  account_id;

    @NotNull(message = "Campo user OBLIGATORIO")
    private Integer user_id;

}
