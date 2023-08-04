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
public class TrasactionGetDto {

    private Integer transactionID;

    private Double amount;

    private String type;

    private LocalDate transactionDate;

}
