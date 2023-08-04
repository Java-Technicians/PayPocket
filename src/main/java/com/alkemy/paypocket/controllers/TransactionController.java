package com.alkemy.paypocket.controllers;
import com.alkemy.paypocket.dtos.PaymetDto;
import com.alkemy.paypocket.dtos.TransaccionDepositDto;
import com.alkemy.paypocket.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.dtos.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "transaction")
@Tag(name = "Transaction", description = "Controlador de Transaction.")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "/deposit")
    @Operation(summary = "Depositar", description = "Registra un deposito.")
    public ResponseEntity<?> registerDeposit(@RequestBody TransactionDto transactionDto){



        try {
            ResponseData<TransaccionDepositDto> responseData = transactionService.saveDeposit(transactionDto);

            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/sendArs/{account_id}")
    @Operation(summary = "Transaccion en ARS", description = "Registra una transaccion en ARS.")
    public ResponseEntity<?> registerTransactionArs(@PathVariable("account_id") Integer user_id, @RequestBody TransactionDto transactionDto){

        try {
            return ResponseEntity.ok(transactionService.saveSentARS(transactionDto, user_id));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping(path = "/sendUSD/{user_id}")
    public ResponseEntity<?> registerTransactionUSD(@PathVariable("user_id") Integer user_id, @RequestBody @Valid TransactionDto transactionDto, BindingResult bindingResult){
        try {

            if (bindingResult.hasErrors()){
                List<String> erros = bindingResult.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(transactionService.saveSentUSD(transactionDto, user_id));

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getAllTransactions(){

        try {

            return ResponseEntity.ok(transactionService.getAllTransactions());

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/{account_id}")
    public ResponseEntity<?> getAllTransactionsByAccount(@PathVariable("account_id") Integer account_id){

        try {

            return ResponseEntity.ok(transactionService.getAllTransactionsByAccount(account_id));

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/paymet")
    public ResponseEntity<?> registerPaymet(@RequestBody PaymetDto paymetDto){

        try {

            System.out.println(paymetDto.getAccount_id());
            return ResponseEntity.ok(transactionService.savePaymet(paymetDto.getAmount(), paymetDto.getAccount_id()));

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
