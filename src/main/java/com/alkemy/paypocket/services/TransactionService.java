package com.alkemy.paypocket.services;


import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.dtos.TransactionDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.Transaction;
import com.alkemy.paypocket.mappers.TransactionMapper;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;

    public ResponseData<TransactionDto> saveDeposit(TransactionDto transactionDto) {

            transactionDto.setType("DEPOSITO"); /*Al ser solicitado por el EndPoint de deposito seteo el type en DEPOSITO*/
            transactionDto.setDescription("DEPOSITO");

            if (validDeposit(transactionDto)){

                Transaction transaction = transactionMapper.toTransaction(transactionDto);

                Account account = accountRepository.findById(transaction.getAccount().getId()).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

                account.setBalance(accountService.updateBalance(transaction));

                TransactionDto newTransactionDto = transactionMapper.transactionDto(transaction);

                accountRepository.save(account);

                transactionRepository.save(transaction);


                return new ResponseData<>(newTransactionDto, "Transaccion Guardada");
            }else{
                throw new RuntimeException("Fondos Insuficientes");
            }




    }

    public Boolean validDeposit(TransactionDto transactionDto){
        return "DEPOSITO".equals(transactionDto.getType()) && transactionDto.getAmount() > 0;

    }

    public ResponseData<Transaction> saveSentARS(TransactionDto transactionDto, Integer user_id) {


            Transaction transactionIncome = saveTransactionIncome(transactionDto);
            Transaction transactionSender = saveTransactionSender(transactionIncome, user_id);

            Account accountIncome = transactionIncome.getAccount();
            Account accountSender = transactionSender.getAccount();


            if (checkTransactionLimit(accountSender, transactionSender.getAmount())){

                if (checkBalance(accountSender, transactionSender.getAmount())){
                    if (accountService.compareAccountCurrencyARS(accountSender, accountIncome)){
                        if (!accountIncome.getId().equals(accountSender.getId())){
                            if (!checkAmount(transactionSender.getAmount())){

                                accountService.updateBalance(transactionIncome);
                                accountService.updateBalance(transactionSender);

                                transactionRepository.save(transactionIncome);
                                transactionRepository.save(transactionSender);

                                return new ResponseData<>(transactionSender, "Transaccion Guardada");
                            }else {
                                throw new RuntimeException("Error no se puede enviar saldos negativos");
                            }
                        }else {
                            throw new IllegalArgumentException("Error No se puede enviar dinero a una misma cuenta");
                        }
                    }else {
                        throw new IllegalArgumentException("Error Ambas cuentas tienen que ser en ARS");
                    }
                }else {
                    throw new IllegalArgumentException("Error de Balance");
                }
            }else {
                throw new IllegalArgumentException("Error de limite de transaccion");
            }

    }

    public List<Transaction> getAllTransactions(){

        List<Transaction> allTransactions = transactionRepository.findAll();

        return allTransactions;

    }


    public Page<Transaction> findAllByPagination(Pageable pageable) {
        Transaction transaction = new Transaction();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Transaction> example = Example.of(transaction, matcher);
        return transactionRepository.findAll(example, pageable);
    }

    public List<Transaction> getAllTransactionsByAccount(Integer accountID){

        Account account = accountRepository.findById(accountID).orElseThrow(() ->  new EntityNotFoundException("Account Inexistente"));

        if (!account.isSoftDelete()){

            List<Transaction> allTransactionsByAccount = transactionRepository.findAllByAccount_Id(accountID);

            return allTransactionsByAccount;
        }else {
            throw new IllegalArgumentException("Cuenta Eliminada");
        }

    }



    private Transaction saveTransactionSender(Transaction transaction, Integer user_Id) {

        Account accountSender = accountRepository.findById(user_Id).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

        Transaction transactionSender = new Transaction();

        transactionSender.setAccount(accountSender);
        transactionSender.setAmount(transaction.getAmount());
        transactionSender.setDescription(transaction.getDescription());
        transactionSender.setTransactionDate(transaction.getTransactionDate());
        transactionSender.setType("PAYMENT");

        return transactionSender;

    }

    private Transaction saveTransactionIncome(TransactionDto transactionDto){
        Transaction transactionIncome = transactionMapper.toTransaction(transactionDto);
        transactionIncome.setType("INCOME");
        return transactionIncome;
    }

    private Boolean checkTransactionLimit(Account account, Double amount) {
        return  amount <= account.getTransactionLimit();
    }

    private Boolean checkBalance(Account account, Double amount) {
        return amount <= account.getBalance();
    }

    private Boolean checkAmount(Double amount){
        return amount <= 0;
    }





}