package com.alkemy.paypocket.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @PostMapping(path = "")
    @Operation(summary = "Registrar", description = "Agrega una cuenta.")
    public ResponseEntity<?> registerAccount(@RequestBody @Valid AccountDto accountDto, BindingResult result) {

        try {
            return ResponseEntity.ok(accountService.saveAccount(accountDto));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping(path = "/{user_id}")
    @Operation(summary = "Obtener", description = "Obtiene una cuenta por id del usuario.")
    public ResponseEntity<?> getAcconuts(
            @PathVariable("user_id") Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        try {
            Page<Account> usersPage = accountService.findAllAccountPaginationByUser(pageRequest);
            List<Account> usersList = usersPage.getContent();
            return ResponseEntity.ok(usersList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PatchMapping(path = "/{account_id}")
    @Operation(summary = "Obtener", description = "Obtiene una cuenta por id de la cuenta.")
    public ResponseEntity<?> updateAccount(@PathVariable("account_id") Integer id,
            @RequestBody @Valid AccountDto accountDto, BindingResult result) {
        return ResponseEntity.ok(accountService.updateAccount(accountDto, id));
    }
}
