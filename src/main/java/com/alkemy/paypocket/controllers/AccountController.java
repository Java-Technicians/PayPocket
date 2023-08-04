package com.alkemy.paypocket.controllers;

import java.util.List;

import com.alkemy.paypocket.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.services.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "accounts")
@Tag(name = "Account", description = "Controlador del Account.")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @PostMapping(path = "")
    @Operation(summary = "Registrar", description = "Agrega una cuenta.")
    public ResponseEntity<?> registerAccount(@RequestBody @Valid AccountDto accountDto, BindingResult result  ){

        try {
            return ResponseEntity.ok(accountService.saveAccount(accountDto));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        

    }

    @GetMapping(path = "/{user_id}" )
    @Operation(summary = "Obtener", description = "Obtiene una cuenta por id del usuario.")
    public ResponseEntity<List<Account>> getAcconuts(@PathVariable("user_id") Integer id){

        List<Account> userAccounts = accountService.findAllAccountByUser(id);
        return ResponseEntity.ok(userAccounts);
    }

    @PatchMapping(path = "/{account_id}")
    @Operation(summary = "Actualizar", description = "Actualiza una cuenta por id de la cuenta.")
    public ResponseEntity<?> updateAccount(@PathVariable("account_id") Integer id, @RequestBody @Valid AccountDto accountDto, BindingResult result){
        return ResponseEntity.ok(accountService.updateAccount(accountDto, id));
    }

    @Operation(summary = "Traer", description = "Trae el balance de todas las cuentas de un usuario.")
    @GetMapping(path = "/balance/{user_id}")
    public ResponseEntity<?> getBalanceByUser(@PathVariable("user_id") Integer user_id){

        try {

            return ResponseEntity.ok(transactionService.getBalance(user_id));

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
