package com.alkemy.paypocket.dtos;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import com.alkemy.paypocket.entities.Account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Fixed Term Deposits DTO", description = "DTO de Fixed Term Deposits")
public class FixedTermDepositsDto {

    @NotNull(message = "Campo createDate es OBLIGATORIO")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @NotNull(message = "Campo closeDate es OBLIGATORIO")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate closeDate;

    @Positive(message = "El valor del amount debe ser mayor que cero")
    @NotNull(message = "Campo amount es OBLIGATORIO")
    private double amount;

    private Integer account_id;

  
}

