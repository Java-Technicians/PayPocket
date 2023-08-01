package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.dtos.CreditCardDto;
import com.alkemy.paypocket.entities.CreditCard;
import com.alkemy.paypocket.services.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditCard")
@Tag(name = "Credit Card", description = "Controlador de Credit Card.")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping(path="/register")
    @Operation(summary = "Registrar", description = "Agrega una Credit Card.")
    public ResponseEntity<CreditCard> createCreditCard(@RequestBody CreditCardDto creditCardDto) {
        CreditCard createdCreditCard = creditCardService.createCreditCard(creditCardDto);
        return new ResponseEntity<>(createdCreditCard, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar", description = "Elimina una Credit Card por id.")
    public ResponseEntity<String> deleteCreditCard(@PathVariable Integer id) {
        try {
            creditCardService.deleteCreditCard(id);
            return ResponseEntity.ok("Tarjeta de crédito eliminada con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
