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
public class TransactionDto {

    @NotBlank(message = "Campo amount Obligatorio")
    private Double amount;

    private String type;

    private String description;

    @NotBlank(message = "Error de referencia")
    private Integer accountId;

}
