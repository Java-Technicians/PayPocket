package com.alkemy.paypocket.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDTO {

    private Double balanceARS;

    private Double balanceUSD;

    private PlazoFijoDto plazoFijoARS;

    private PlazoFijoDto plazoFijoUSD;

}
