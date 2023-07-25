package com.alkemy.paypocket.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.paypocket.dtos.FixedTermDepositsDto;
import com.alkemy.paypocket.services.FixedTermDepositsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "fixedTermDeposit")
public class FixedTermDepositsController {

    @Autowired
    FixedTermDepositsService fixedDeposits;

    @PostMapping(path = "/simulate")
    public ResponseEntity<?> simulate(@RequestBody @Valid FixedTermDepositsDto depositDto, BindingResult result) {

        //Errores varios si se envia info faltante
        if (result.hasErrors()) {
            List<String> erros = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(erros);
        }


        try {
            return ResponseEntity.ok(fixedDeposits.simulate(depositDto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }

}