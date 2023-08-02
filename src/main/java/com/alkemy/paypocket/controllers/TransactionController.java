package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.dtos.TransactionDto;
import com.alkemy.paypocket.entities.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "transaction")
@Tag(name = "Transaction", description = "Controlador de Transaction.")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "/deposit")
    @Operation(summary = "Depositar", description = "Registra un deposito.")
    public ResponseEntity<?> registerDeposit(@RequestBody TransactionDto transactionDto) {

        try {
            ResponseData<TransactionDto> responseData = transactionService.saveDeposit(transactionDto);

            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/sendArs/{user_id}")
    @Operation(summary = "Transaccion en ARS", description = "Registra una transaccion en ARS.")
    public ResponseEntity<?> registerTransactionArs(@PathVariable("user_id") Integer user_id,
            @RequestBody TransactionDto transactionDto) {

        try {
            return ResponseEntity.ok(transactionService.saveSentARS(transactionDto, user_id));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "")
    public ResponseEntity<?> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        try {
            Page<Transaction> usersPage = transactionService.findAllByPagination(pageRequest);
            List<Transaction> usersList = usersPage.getContent();
            return ResponseEntity.ok(usersList);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/{account_id}")
    public ResponseEntity<?> getAllTransactionsByAccount(@PathVariable("account_id") Integer account_id) {

        try {

            return ResponseEntity.ok(transactionService.getAllTransactionsByAccount(account_id));

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
