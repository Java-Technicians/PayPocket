package com.alkemy.paypocket.services;


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

    public ResponseData<Transaction> saveSentARS(TransactionDto transactionDto, Integer user_id) {

        try {

            Transaction transactionIncome = saveTransactionIncome(transactionDto);
            Transaction transactionSender = saveTransactionSender(transactionIncome, user_id);

            Account accountIncome = transactionIncome.getAccount();
            Account accountSender = transactionSender.getAccount();


            if (checkTransactionLimit(accountSender, transactionSender.getAmount())){

                if (checkBalance(accountSender, transactionSender.getAmount())){
                    if (accountService.compareAccountCurrency(accountSender, accountIncome)){
                        if (!accountIncome.getId_account().equals(accountSender.getId_account())){

                            accountService.updateBalance(transactionIncome);
                            accountService.updateBalance(transactionSender);

                            transactionRepository.save(transactionIncome);
                            transactionRepository.save(transactionSender);

                            return new ResponseData<>(transactionSender, "Transaccion Guardada");
                        }else {
                            return new ResponseData<>(null, "Error de Coincidencia de tipo de Account");
                        }
                    }else {
                        return new ResponseData<>(null, "Error de Coincidencia de tipo de Currency");
                    }
                }else {
                    return new ResponseData<>(null, "Error de Balance");
                }
            }else {
                return new ResponseData<>(null, "Error de limite transaccion");
            }



        } catch (Exception e) {
            e.printStackTrace(); // Imprime el error en la consola (opcional)
            return new ResponseData<>(null, "Error al registrar transaccion");
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



}