package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.dtos.UserDto;
import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.repositories.RoleRepository;
import com.alkemy.paypocket.repositories.UserRepository;
import com.alkemy.paypocket.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto userDto, BindingResult result ){

        if (result.hasErrors()){
            List<String> erros = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(erros);
        }

        return ResponseEntity.ok(userService.saveUser(userDto));
    }



}
