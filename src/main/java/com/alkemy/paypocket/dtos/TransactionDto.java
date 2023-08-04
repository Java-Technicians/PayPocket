package com.alkemy.paypocket.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Transaction DTO", description = "DTO de Transaction")
public class TransactionDto {

    @NotNull(message = "Campo amount Obligatorio")
    private Double amount;

    private String type;

    private String description;

    @NotNull(message = "Error de referencia")
    private Integer accountDestination;

}