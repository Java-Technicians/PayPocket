package com.alkemy.paypocket.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlazoFijoDto {

    private Double amount;

    private Double interest;

    private LocalDate creationDate;

    private LocalDate closingDate;

}
