package com.alkemy.paypocket.mappers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.repositories.AccountRepository;

public class AccountMapper {
    @Autowired
    AccountRepository accountRepository;

    public Account toAccount(AccountDto accountDto) {

        if (accountRepository.existsByCurrency(accountDto.getCurrency())) {
            throw new IllegalArgumentException("cuenta registrada");
        }

        Account account = new Account();

        account.setBalance(accountDto.getBalance());
        account.setCurrency(accountDto.getCurrency());
        account.setCreationDate(LocalDate.now());
        account.setSoftDelete(false);
        account.setTransactionLimit(account.getTransactionLimit());
        account.setId_account(account.getId_account());

        return account;

    }

}
