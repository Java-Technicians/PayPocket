package com.alkemy.paypocket.controllers;
import com.alkemy.paypocket.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.paypocket.dtos.PaymentDto;
import com.alkemy.paypocket.dtos.UserDto;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.dtos.TransactionDto;
import com.alkemy.paypocket.entities.Transaction;
import com.alkemy.paypocket.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "/deposit")
    public ResponseEntity<ResponseData<Transaction>> registerDeposit(@RequestBody TransactionDto transactionDto){

        ResponseData<Transaction> responseData = transactionService.saveDeposit(transactionDto);

        return ResponseEntity.ok(responseData);
    }

    @PostMapping(path = "/sendArs/{user_id}")
    public ResponseEntity<?> registerTransactionArs(@PathVariable("user_id") Integer user_id, @RequestBody TransactionDto transactionDto){

        return ResponseEntity.ok(transactionService.saveSentARS(transactionDto, user_id));
    }

}
