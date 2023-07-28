package com.alkemy.paypocket.services;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.entities.Transaction;
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

    public Double updateBalance(Transaction transaction){

        Account account = accountRepository.findById(transaction.getAccount().getId_account()).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

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

    public Boolean compareAccountCurrency(Account accountSender, Account accountIncome){

        List<Account> accountsSender = findAllAccountByUser(accountSender.getUser().getId());
        List<Account> accountsIncome = findAllAccountByUser(accountIncome.getUser().getId());

        // Filtrar los Account que tengan el tipo ARS (currency)

        List<Account> arsAccountSender = accountsSender.stream()
                .filter(account -> "ARS".equals(account.getCurrency()))
                .collect(Collectors.toList());

        List<Account> arsAccountIncome = accountsIncome.stream()
                .filter(account -> "ARS".equals(account.getCurrency()))
                .collect(Collectors.toList());

        Boolean haveARSaccountSender = !arsAccountSender.isEmpty();
        Boolean haveARSaccountIncome = !arsAccountIncome.isEmpty();


        return (haveARSaccountIncome && haveARSaccountSender);


    }


}