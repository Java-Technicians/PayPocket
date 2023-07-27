package com.alkemy.paypocket.dtos;


import java.time.LocalDate;

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

    private Account account;

  
}

