package com.alkemy.paypocket.services;

import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.dtos.CreditCardDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.CreditCard;
import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.CreditCardRepository;
import com.alkemy.paypocket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final AccountRepository accountRepository;

    @Autowired    public CreditCardService(CreditCardRepository creditCardRepository, AccountRepository accountRepository) {
        this.creditCardRepository = creditCardRepository;
        this.accountRepository = accountRepository;

    }
    @Autowired
    private UserRepository userRepository;

    private Account convertToAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setCurrency(accountDto.getCurrency());
        account.setTransactionLimit(accountDto.getTransactionLimit() != null ? accountDto.getTransactionLimit() : 0.0);
        account.setBalance(accountDto.getBalance() != null ? accountDto.getBalance() : 0.0);
        account.setCreationDate(LocalDate.now());
        account.setSoftDelete(false);

        return account;
    }

    @Transactional
    public CreditCard createCreditCard(CreditCardDto creditCardDto) {
        List<Account> accounts = accountRepository.findAllByUser_Id(creditCardDto.getAccount_id());

        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }

        Account account = accounts.get(0);
        CreditCard creditCard = new CreditCard();
        creditCard.setName(creditCardDto.getName());
        creditCard.setAmountAvailable(creditCardDto.getAmountAvailable());
        creditCard.setAmount(creditCardDto.getAmount());
        creditCard.setAccount(account);
        creditCard.setCreationDate(LocalDate.now());
        creditCard.setSoftDelete(false);

        Integer userId = creditCardDto.getUser_id();
        Optional<User> user = userRepository.findById(userId);
        creditCard.setUser(user.get());




        return creditCardRepository.save(creditCard);
    }
}




