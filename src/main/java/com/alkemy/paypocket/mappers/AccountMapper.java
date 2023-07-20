package com.alkemy.paypocket.mappers;

import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AccountMapper {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public Account toAccount(AccountDto accountDto) {

        Account account = new Account();

        account.setBalance(0);
        account.setCurrency(accountDto.getCurrency());
        account.setCreationDate(LocalDate.now());
        account.setSoftDelete(false);

        if ("USD".equals(accountDto.getCurrency())){
            account.setTransactionLimit(1000);
        }else if (("ARS".equals((accountDto.getCurrency())))){
            account.setTransactionLimit(300000);
        }else{
            account.setTransactionLimit(500);
        }


        Integer userId = accountDto.getUser_id();

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario con ID " + userId + " no encontrado."));

        account.setUser(user);

        return account;
    }


}
