package com.alkemy.paypocket.mappers;

import com.alkemy.paypocket.dtos.CreditCardDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.CreditCard;
import com.alkemy.paypocket.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class CreditCardMapper {
    @Autowired
    AccountRepository accountRepository;

    public CreditCard toCreditCard(CreditCardDto creditCardDto){

        CreditCard creditCard = new CreditCard();

        creditCard.setName(creditCardDto.getName());
        creditCard.setAmountAvailable(creditCardDto.getAmountAvailable());
        creditCard.setAmount(creditCardDto.getAmount());
        creditCard.setCreationDate(LocalDate.now());
        creditCard.setSoftDelete(false);

        Optional<Account> account = accountRepository.findById(creditCardDto.getAccount_id());

        if (!account.isPresent() || account.get().isSoftDelete()){
            throw  new IllegalArgumentException("Usuario con ID " + account.get().getId() + " no encontrado.");
        }

        creditCard.setAccount(account.get());

        return creditCard;

    }

    public CreditCardDto toCreditCardDto(CreditCard creditCard){

        CreditCardDto creditCardDto = new CreditCardDto();

        creditCardDto.setName(creditCard.getName());
        creditCardDto.setAmountAvailable(creditCard.getAmountAvailable());
        creditCardDto.setAmount(creditCard.getAmount());
        creditCardDto.setAccount_id(creditCard.getAccount().getId());

        return creditCardDto;

    }

}
