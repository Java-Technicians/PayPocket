package com.alkemy.paypocket.services;
import java.time.LocalDate;
import java.util.List;

import java.util.Optional;

import java.util.stream.Collectors;


import com.alkemy.paypocket.message.ResponseData;
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

    public ResponseData<Account> updateAccount(AccountDto accountDto, Integer id) {

        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            try {
                Account existingAccount = optionalAccount.get();

                existingAccount.setTransactionLimit(accountDto.getTransactionLimit());

                existingAccount.setUpdateDate(LocalDate.now());
                accountRepository.save(existingAccount);

                return new ResponseData<>(existingAccount, "Actualizacion exitosa");
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseData<>(null, "Error al actualizar la cuenta");
            }
        } else {
            return new ResponseData<>(null, "Cuenta no encontrada");
        }
    }


}
