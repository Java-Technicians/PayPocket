package com.alkemy.paypocket.mappers;


import com.alkemy.paypocket.dtos.*;
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

        newTransaction.setAccount(accountRepository.findById(transactionDto.getAccountDestination()).get());
        newTransaction.setType(transactionDto.getType());
        newTransaction.setAmount(transactionDto.getAmount());
        newTransaction.setTransactionDate(LocalDate.now());
        newTransaction.setDescription(transactionDto.getDescription());

        return newTransaction;

    }

    public TransactionDto transactionDto(Transaction transaction){

        TransactionDto newTransactionDto = new TransactionDto();

        newTransactionDto.setType(transaction.getType());
        newTransactionDto.setAmount(transaction.getAmount());
        newTransactionDto.setAccountDestination(transaction.getAccount().getId());
        newTransactionDto.setDescription(transaction.getDescription());

        return  newTransactionDto;

    }

    public TransaccionDepositDto toTransaccionDto(Transaction transaction){

        TransaccionDepositDto transaccionDepositDto = new TransaccionDepositDto();

        transaccionDepositDto.setAccountDestination(transaction.getAccount().getId());
        transaccionDepositDto.setAmount(transaction.getAmount());
        transaccionDepositDto.setTransactionDate(transaction.getTransactionDate());

        return transaccionDepositDto;
    }

    public PaymentGetDto toPayment(Transaction transaction){

        PaymentGetDto paymentGetDto = new PaymentGetDto();

        paymentGetDto.setAmount(transaction.getAmount());
        paymentGetDto.setPaymetDate(transaction.getTransactionDate());

        return paymentGetDto;

    }

    public SendARSDto toSendARS(Transaction transaction, Integer accountOrigin){

        SendARSDto sendARSDto = new SendARSDto();

        sendARSDto.setAccountDestination(transaction.getAccount().getId());
        sendARSDto.setAccountOrigin(accountOrigin);
        sendARSDto.setAmountSender(transaction.getAmount());
        sendARSDto.setTransactionDate(transaction.getTransactionDate());

        return sendARSDto;
    }

    public TrasactionGetDto trasactionGetDto(Transaction transaction){

        TrasactionGetDto trasactionGetDto = new TrasactionGetDto();

        trasactionGetDto.setTransactionID(transaction.getId());
        trasactionGetDto.setAmount(transaction.getAmount());
        trasactionGetDto.setType(transaction.getType());
        trasactionGetDto.setTransactionDate(transaction.getTransactionDate());

        return trasactionGetDto;

    }

}
