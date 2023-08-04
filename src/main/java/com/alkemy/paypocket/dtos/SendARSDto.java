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
public class SendARSDto {

    private Integer accountOrigin;

    private Integer accountDestination;

    private Double amountSender;

    private LocalDate transactionDate;

}
