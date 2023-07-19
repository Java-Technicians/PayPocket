package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.alkemy.paypocket.dtos.UserDto;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "auth")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping(path = "user/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable("user_id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();

    }
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
