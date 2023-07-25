package com.alkemy.paypocket.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "fixedTermDeposit")
public class FixedTermDepositsController{

    @PostMapping(path = "/simulate")
    public ResponseEntity<?> registerAccount(@RequestBody  Object accountDto  ){


        return ResponseEntity.ok("OK");

    }



}