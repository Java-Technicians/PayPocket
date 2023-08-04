package com.alkemy.paypocket.mappers;

import com.alkemy.paypocket.dtos.PlazoFijoDto;
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

    public PlazoFijoDto ToplazoFijoDto (Fixed_term_deposits fixedTermDeposits){

        PlazoFijoDto plazoFijo = new PlazoFijoDto();

        plazoFijo.setAmount(fixedTermDeposits.getAmount());
        plazoFijo.setInterest(fixedTermDeposits.getInterest());
        plazoFijo.setCreationDate(fixedTermDeposits.getCreationDate());
        plazoFijo.setClosingDate(fixedTermDeposits.getClosingDate());

        return plazoFijo;

    }
    
}
