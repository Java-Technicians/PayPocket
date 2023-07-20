package com.alkemy.paypocket.mappers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.UserRepository;
@Component
public class AccountMapper {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

   

 public Account toAccount(AccountDto accountDto) {



    Account account = new Account();

    account.setBalance(accountDto.getBalance());
    account.setCurrency(accountDto.getCurrency());
    account.setCreationDate(LocalDate.now());
    account.setSoftDelete(false);
    account.setTransactionLimit(accountDto.getTransactionLimit());

    Integer userId = accountDto.getUser_id();
    
    User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario con ID " + userId + " no encontrado."));

    account.setUser(user);

    return account;
}


}
