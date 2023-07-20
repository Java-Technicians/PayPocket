package com.alkemy.paypocket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.mappers.AccountMapper;
import com.alkemy.paypocket.repositories.AccountRepository;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    public List<Account> findAllUser(Integer id_user) {
        return accountRepository.findAllByUser_Id(id_user);
    }



    public Account saveAccount(AccountDto accountDto) {

        Account newAccount = accountMapper.toAccount(accountDto);
        accountRepository.save(newAccount);

        return newAccount;
    }
}
