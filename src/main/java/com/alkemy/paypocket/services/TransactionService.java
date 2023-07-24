package com.alkemy.paypocket.services;

import com.alkemy.paypocket.Message.ResponseData;
import com.alkemy.paypocket.dtos.TransactionDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.Transaction;
import com.alkemy.paypocket.mappers.TransactionMapper;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    AccountRepository accountRepository;

    public ResponseData<Transaction> saveTransaction(TransactionDto transactionDto){

        try {
            transactionDto.setType("DEPOSITO"); /*Al ser solicitado por el EndPoint de deposito seteo el type en DEPOSITO*/

            Transaction transaction = transactionMapper.toTransaction(transactionDto);

            Account account = accountRepository.findById(transaction.getAccount().getId_account()).get();

            account.setBalance(transaction.getAmount());

            accountRepository.save(account);

            transactionRepository.save(transaction);


            return new ResponseData<>(transaction,"Transaccion Guardada");

        }catch(Exception e){
            e.printStackTrace(); // Imprime el error en la consola (opcional)
            return new ResponseData<>(null,"Error al registrar transaccion");
        }




    }

}
