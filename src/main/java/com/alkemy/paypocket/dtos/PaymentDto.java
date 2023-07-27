package com.alkemy.paypocket.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    @NotBlank(message = "Campo amount obligatorio")
    private Double amount;

    private String type;

    private String description;

    private Integer senderAccountId;

    @NotBlank(message = "Error de referencia")
    private Integer recipientAccountId;

}