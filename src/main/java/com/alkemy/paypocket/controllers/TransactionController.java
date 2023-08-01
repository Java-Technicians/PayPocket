package com.alkemy.paypocket.controllers;
import com.alkemy.paypocket.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.dtos.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "transaction")
@Tag(name = "Transaction", description = "Controlador de Transaction.")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "/deposit")
    @Operation(summary = "Depositar", description = "Registra un deposito.")
    public ResponseEntity<ResponseData<TransactionDto>> registerDeposit(@RequestBody TransactionDto transactionDto){

        ResponseData<TransactionDto> responseData = transactionService.saveDeposit(transactionDto);

        return ResponseEntity.ok(responseData);
    }

    @PostMapping(path = "/sendArs/{user_id}")
    @Operation(summary = "Transaccion en ARS", description = "Registra una transaccion en ARS.")
    public ResponseEntity<?> registerTransactionArs(@PathVariable("user_id") Integer user_id, @RequestBody TransactionDto transactionDto){

        return ResponseEntity.ok(transactionService.saveSentARS(transactionDto, user_id));
    }

}
