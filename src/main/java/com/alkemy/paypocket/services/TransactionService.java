package com.alkemy.paypocket.services;


import com.alkemy.paypocket.dtos.*;
import com.alkemy.paypocket.entities.Fixed_term_deposits;
import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.mappers.FixedTermDepositsMapper;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.Transaction;
import com.alkemy.paypocket.mappers.TransactionMapper;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.Fixed_term_depositsRepository;
import com.alkemy.paypocket.repositories.TransactionRepository;
import com.alkemy.paypocket.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


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

    @Autowired
    UserRepository userRepository;

    @Autowired
    Fixed_term_depositsRepository fixedTermDepositsRepository;

    @Autowired
    FixedTermDepositsMapper fixedTermDepositsMapper;

    public ResponseData<TransaccionDepositDto> saveDeposit(TransactionDto transactionDto) {

            transactionDto.setType("DEPOSITO"); /*Al ser solicitado por el EndPoint de deposito seteo el type en DEPOSITO*/
            transactionDto.setDescription("DEPOSITO");

            if (validDeposit(transactionDto)){

                Transaction transaction = transactionMapper.toTransaction(transactionDto);

                Account account = accountRepository.findById(transaction.getAccount().getId()).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));

                account.setBalance(accountService.updateBalance(transaction));

                TransaccionDepositDto newTransaccion = transactionMapper.toTransaccionDto(transaction);

                accountRepository.save(account);

                transactionRepository.save(transaction);


                return new ResponseData<>(newTransaccion, "Deposito Guardado");
            }else{
                throw new RuntimeException("Fondos Insuficientes");
            }




    }

    public Boolean validDeposit(TransactionDto transactionDto){
        return "DEPOSITO".equals(transactionDto.getType()) && transactionDto.getAmount() > 0;

    }

    public ResponseData<SendARSDto> saveSentARS(TransactionDto transactionDto, Integer user_id) {


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

                                return new ResponseData<>(transactionMapper.toSendARS(transactionIncome, user_id), "Transaccion Guardada");
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

    public ResponseData<Transaction> saveSentUSD(TransactionDto transactionDto, Integer user_Id){

        Transaction transactionIncome = saveTransactionIncome(transactionDto);
        Transaction transactionSender = saveTransactionSender(transactionIncome, user_Id);

        Account accountIncome = transactionIncome.getAccount();
        Account accountSender = transactionSender.getAccount();


        if (checkTransactionLimit(accountSender, transactionSender.getAmount())){

            if (checkBalance(accountSender, transactionSender.getAmount())){
                if (accountService.compareAccountCurrencyUSD(accountSender, accountIncome)){
                    if (!accountIncome.getId().equals(accountSender.getId())){
                        if (!checkAmount(transactionSender.getAmount())){

                            accountService.updateBalance(transactionIncome);
                            accountService.updateBalance(transactionSender);

                            transactionRepository.save(transactionIncome);
                            transactionRepository.save(transactionSender);

                            return new ResponseData<>(transactionSender, "Transaccion Exitosa");
                        }else {
                            throw new RuntimeException("Error no se puede enviar saldos negativos");
                        }
                    }else {
                        throw new IllegalArgumentException("Error No se puede enviar dinero a una misma cuenta");
                    }
                }else {
                    throw new IllegalArgumentException("Error Ambas cuentas tienen que ser en USD");
                }
            }else {
                throw new IllegalArgumentException("Error de Balance");
            }
        }else {
            throw new IllegalArgumentException("Error de limite de transaccion");
        }
    }

    public ResponseData<PaymentGetDto> savePaymet(Double amount, Integer account_Id){

        Account account = accountRepository.findById(account_Id).orElseThrow(() -> new EntityNotFoundException("Cuenta inexistente"));


        if (checkBalance(account, amount) && !checkAmount(amount)){

            Transaction newTransaction = new Transaction();

            newTransaction.setType("PAYMENT");
            newTransaction.setDescription("PAYMENT");
            newTransaction.setAmount(amount);
            newTransaction.setAccount(account);
            newTransaction.setTransactionDate(LocalDate.now());

            accountService.updateBalance(newTransaction);
            transactionRepository.save(newTransaction);


            return new ResponseData<>(transactionMapper.toPayment(newTransaction), "Transaccion Exitosa");

        }else {
            throw new IllegalArgumentException("Error: Verifique el balance o el monto");
        }
    }
    public List<Transaction> getAllTransactions(){

        List<Transaction> allTransactions = transactionRepository.findAll();

        return allTransactions;

    }

    public List<TrasactionGetDto> getAllTransactionsByAccount(Integer accountID){

        Account account = accountRepository.findById(accountID).orElseThrow(() ->  new EntityNotFoundException("Account Inexistente"));

        if (!account.isSoftDelete()){

            List<Transaction> allTransactionsByAccount = transactionRepository.findAllByAccount_Id(accountID);
            List<TrasactionGetDto> trasactionGetDtosList = allTransactionsByAccount.stream()
                    .map(transactionMapper::trasactionGetDto)
                    .collect(Collectors.toList());

            return trasactionGetDtosList;
        }else {
            throw new IllegalArgumentException("Cuenta Eliminada");
        }

    }

    public ResponseData<BalanceDTO> getBalance(Integer user_Id) {

        User user = userRepository.findById(user_Id).orElseThrow(() -> new EntityNotFoundException("Usuario no Inecistente"));

        List<Account> allAccount = accountService.findAllAccountByUser(user.getId());

        Account accountARS = allAccount.stream()
                .filter(account -> !account.isSoftDelete() && !account.getUser().getSoftDelete())
                .filter(account -> "ARS".equals(account.getCurrency()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Cuenta Inexistente"));

        Account accountUSD = allAccount.stream()
                .filter(account -> !account.isSoftDelete() && !account.getUser().getSoftDelete())
                .filter(account -> "USD".equals(account.getCurrency()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Cuenta Inexistente"));

        Fixed_term_deposits plazoFijoUSD = fixedTermDepositsRepository.findByAccount_Id(accountUSD.getId()).get();
        Fixed_term_deposits plazoFijoARS = fixedTermDepositsRepository.findByAccount_Id(accountARS.getId()).get();

        BalanceDTO balanceDTO = new BalanceDTO();

        balanceDTO.setBalanceARS(accountARS.getBalance());
        balanceDTO.setBalanceUSD(accountUSD.getBalance());
        balanceDTO.setPlazoFijoARS(fixedTermDepositsMapper.ToplazoFijoDto(plazoFijoARS));
        balanceDTO.setPlazoFijoUSD(fixedTermDepositsMapper.ToplazoFijoDto(plazoFijoUSD));

        return new ResponseData<>(balanceDTO, "Consulta exitosa");
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