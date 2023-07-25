package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.dtos.PaymentDto;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.dtos.TransactionDto;
import com.alkemy.paypocket.entities.Transaction;
import com.alkemy.paypocket.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "/deposit")
    public ResponseEntity<ResponseData<Transaction>> registerTransaction(@RequestBody TransactionDto transactionDto){

        ResponseData<Transaction> responseData = transactionService.saveDeposit(transactionDto);

        return ResponseEntity.ok(responseData);
    }

    @PostMapping(path = "/sendArs")
    public ResponseEntity<ResponseData<Transaction>> registerTransactionArs(@RequestBody PaymentDto paymentDto){

        ResponseData<Transaction> responseData = transactionService.saveSent(paymentDto);

        return ResponseEntity.ok(responseData);
    }

}
