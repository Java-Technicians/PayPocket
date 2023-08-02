package com.alkemy.paypocket.services;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.mappers.AccountMapper;
import com.alkemy.paypocket.repositories.AccountRepository;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;

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


    public Page<Account> findAllAccountPaginationByUser(Pageable pageable) {
        Account account = new Account();
        account.setSoftDelete(false);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Account> example = Example.of(account, matcher);
        return accountRepository.findAll(example, pageable);
    }



    public ResponseData<AccountDto> saveAccount(AccountDto accountDto) {

        try {
            Account newAccount = accountMapper.toAccount(accountDto);

            if (accountRepository.existsByUserIdAndCurrency(newAccount.getUser().getId(), newAccount.getCurrency())){
                throw new IllegalArgumentException("Ya existe un currency de este tipo asociado a este usuario");
            }

            AccountDto newAccountDto = accountMapper.toAccountDto(newAccount);

            accountRepository.save(newAccount);

            return new ResponseData<>(newAccountDto, "Registro exitoso");
        }catch (Exception e){
            return new ResponseData<>(null, e.getMessage());
        }

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

    public Double updateBalance(Transaction transaction){

        Account account = accountRepository.findById(transaction.getAccount().getId()).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        if ("DEPOSITO".equals(transaction.getType()) || "INCOME".equals(transaction.getType())) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        } else if ("PAYMENT".equals(transaction.getType())) {
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else {
            throw new IllegalArgumentException("Tipo de transacción inválido");
        }

        accountRepository.save(account);

        return account.getBalance();

    }

    public Boolean compareAccountCurrencyARS(Account accountSender, Account accountIncome){

        return "ARS".equals(accountSender.getCurrency()) && "ARS".equals(accountIncome.getCurrency());

    }


}