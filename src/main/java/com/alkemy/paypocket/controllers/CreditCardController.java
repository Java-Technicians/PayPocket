package com.alkemy.paypocket.controllers;


import com.alkemy.paypocket.dtos.CreditCardDto;
import com.alkemy.paypocket.services.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/creditCard")
@Tag(name = "Credit Card", description = "Controlador de Credit Card.")
public class CreditCardController {


    @Autowired
    CreditCardService creditCardService;


    @PostMapping(path = "/register")
    public ResponseEntity<?> newCreditCart (@RequestBody @Valid CreditCardDto creditCardDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            List<String> erros = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(erros);
        }

        try {
            System.out.println(creditCardDto.getAmountAvailable());
            return ResponseEntity.ok(creditCardService.saveCreditCard(creditCardDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar", description = "Elimina una Credit Card por id.")
    @DeleteMapping(path = "/{card_id}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable("card_id") Integer id){
        try {

            creditCardService.deleteCreditCard(id);

            return ResponseEntity.ok("Tarjeta Eliminada");

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
