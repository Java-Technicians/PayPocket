package com.alkemy.paypocket.services;

import com.alkemy.paypocket.dtos.PaymentDto;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.dtos.TransactionDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.Transaction;
import com.alkemy.paypocket.mappers.TransactionMapper;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ResponseData<Transaction> saveDeposit(TransactionDto transactionDto) {

        try {
            transactionDto.setType("DEPOSITO"); /*Al ser solicitado por el EndPoint de deposito seteo el type en DEPOSITO*/

            if (validDeposit(transactionDto)){

                Transaction transaction = transactionMapper.toTransaction(transactionDto);

                Account account = accountRepository.findById(transaction.getAccount().getId_account()).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

                account.setBalance(accountService.updateBalance(transaction));

                accountRepository.save(account);

                transactionRepository.save(transaction);

            return new ResponseData<>(transaction, "Transaccion Guardada");
            }else{
                return new ResponseData<>(null, "Transaccion no valida");
            }


        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola (opcional)
            return new ResponseData<>(null, "Error al registrar transaccion");
        }

    }

    public Boolean validDeposit(TransactionDto transactionDto){
        return "DEPOSITO".equals(transactionDto.getType()) && transactionDto.getAmount() > 0;

    }

    public ResponseData<Transaction> saveSent(TransactionDto transactionDto, Integer user_id) {

        try {
            Transaction transactionIncome = transactionMapper.toTransaction(transactionDto);
            Account accountPaymentSender = accountRepository.findById(user_id).orElseThrow(() -> new Exception("No existe la cuenta"));
            Transaction transactionSender = saveTransactionSender(transactionIncome, accountPaymentSender);
            transactionRepository.save(transactionIncome);

            if(checkBalance(accountPaymentSender, transactionSender.getAmount()) && checkTransactionLimit(accountPaymentSender, transactionSender.getAmount())){
                transactionIncome.setType("INCOME");
                transactionSender.setType("PAYMENT");
                accountService.updateBalance(transactionIncome);
                accountService.updateBalance(transactionSender);

            }
        return new ResponseData<>(null, "Transaccion Guardada");

        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola (opcional)
            return new ResponseData<>(null, "Error al registrar transaccion");
        }
    }

    private Transaction saveTransactionSender(Transaction transaction, Account account) {
        Transaction transactionSender = new Transaction();
        transactionSender.setAccount(account);
        transactionSender.setAmount(transaction.getAmount());
        transactionSender.setDescription(transaction.getDescription());
        transactionSender.setTransactionDate(transaction.getTransactionDate());
        transactionSender.setType("PAYMENT");
        return transactionRepository.save(transactionSender);

    }

    private Boolean checkTransactionLimit(Account account, Double amount) {
        return  amount < account.getTransactionLimit();
    }

    private Boolean checkBalance(Account account, Double amount) {
        return amount < account.getBalance();
    }
}