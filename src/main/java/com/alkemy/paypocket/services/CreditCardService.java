package com.alkemy.paypocket.services;

import com.alkemy.paypocket.dtos.CreditCardDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.CreditCard;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, AccountRepository accountRepository) {
        this.creditCardRepository = creditCardRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public CreditCard createCreditCard(CreditCardDto creditCardDto) {
        Account account = accountRepository.findById(creditCardDto.getAccount_id())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        CreditCard creditCard = new CreditCard();
        creditCard.setName(creditCardDto.getName());
        creditCard.setAmountAvailable(creditCardDto.getAmountAvailable());
        creditCard.setAmount(creditCardDto.getAmount());
        creditCard.setAccount(account);
        creditCard.setCreationDate(LocalDate.now());
        creditCard.setSoftDelete(false);

        return creditCardRepository.save(creditCard);
    }

}

