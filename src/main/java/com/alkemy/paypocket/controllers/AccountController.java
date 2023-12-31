package com.alkemy.paypocket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.services.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping(path = "")
    public ResponseEntity<?> registerAccount(@RequestBody @Valid AccountDto accountDto, BindingResult result  ){

        try {
            return ResponseEntity.ok(accountService.saveAccount(accountDto));
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        

    }



    @GetMapping(path = "/{user_id}" )
    public ResponseEntity<List<Account>> getAcconuts(@PathVariable("user_id") Integer id){

        List<Account> userAccounts = accountService.findAllAccountByUser(id);
        return ResponseEntity.ok(userAccounts);
    }
}
