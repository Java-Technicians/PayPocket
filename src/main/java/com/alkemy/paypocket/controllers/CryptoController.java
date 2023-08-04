package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.dtos.CryptoDto;
import com.alkemy.paypocket.repositories.CryptoRepository;
import com.alkemy.paypocket.services.CryptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "crypto")
@Tag(name = "Crypto", description = "Controlador de Crypto.")
public class CryptoController {

    @Autowired
    CryptoService cryptoService;

    @Autowired
    CryptoRepository cryptoRepository;

    @PostMapping(path = "/register")
    @Operation(summary = "Crear", description = "Crea una crypto.")
    public ResponseEntity<?> registerCrypto(@RequestBody @Valid CryptoDto cryptoDto) {

        try {
            return ResponseEntity.ok(cryptoService.saveCrypto(cryptoDto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{crypto_id}" )
    @Operation(summary = "Obtener", description = "Obtiene una crypto por id.")
    public ResponseEntity<?> getCrypto(@PathVariable("crypto_id") Integer id){
        return ResponseEntity.ok(cryptoRepository.findById(id));
    }

}
