package com.alkemy.paypocket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.services.AccountService;

@RestController
@RequestMapping(path = "accounts")
public class AccountController {

    @Autowired
    AccountService userService;



    @GetMapping(path = "/{user_id}" )
    public ResponseEntity<List<Account>> getAcconuts(@PathVariable("user_id") Integer id){

        List<Account> userAccounts = userService.findAll();
        return ResponseEntity.ok(userAccounts);
    }
    
}
