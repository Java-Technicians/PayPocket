package com.alkemy.paypocket.mappers;

import org.springframework.stereotype.Component;

import com.alkemy.paypocket.dtos.FixedTermDepositsDto;
import com.alkemy.paypocket.entities.Fixed_term_deposits;

@Component
public class FixedTermDepositsMapper {


    public Fixed_term_deposits toFixedDeposit(FixedTermDepositsDto transactionDto){

        Fixed_term_deposits newFixedDeposit = new Fixed_term_deposits();

       
        newFixedDeposit.setClosingDate(transactionDto.getCloseDate());
        newFixedDeposit.setCreationDate(transactionDto.getCreateDate());
        newFixedDeposit.setAmount(transactionDto.getAmount());

        return newFixedDeposit;
    }
    
}
