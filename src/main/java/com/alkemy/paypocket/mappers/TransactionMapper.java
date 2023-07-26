package com.alkemy.paypocket.mappers;

import com.alkemy.paypocket.dtos.TransactionDto;
import com.alkemy.paypocket.entities.Transaction;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class TransactionMapper {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;


    public Transaction toTransaction(TransactionDto transactionDto){

        Transaction newTransaction = new Transaction();

        newTransaction.setAccount(accountRepository.findById(transactionDto.getAccountId()).get());
        newTransaction.setType(transactionDto.getType());
        newTransaction.setAmount(transactionDto.getAmount());
        newTransaction.setTransactionDate(LocalDate.now());
        newTransaction.setDescription(transactionDto.getDescription());

        return newTransaction;
    }

}
