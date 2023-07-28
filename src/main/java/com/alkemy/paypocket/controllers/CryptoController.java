package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.dtos.CryptoDto;
import com.alkemy.paypocket.services.CryptoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "crypto")
public class CryptoController {

    @Autowired
    CryptoService cryptoService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerCrypto(@RequestBody @Valid CryptoDto cryptoDto) {

        try {
            return ResponseEntity.ok(cryptoService.saveCrypto(cryptoDto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
