package com.alkemy.paypocket.services;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Account> findAllAccountByUser(Integer id_user) {


        List<Account> allAccounts = accountRepository.findAllByUser_Id(id_user);

        return allAccounts.stream()
                .filter(account -> !account.isSoftDelete() && !account.getUser().getSoftDelete())
                .collect(Collectors.toList());
    }



    public Account saveAccount(AccountDto accountDto)throws Exception {
        
        Account newAccount = accountMapper.toAccount(accountDto);
        accountRepository.save(newAccount);
    
        return newAccount;
    }
}
