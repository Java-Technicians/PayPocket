package com.alkemy.paypocket.controller;

import com.alkemy.paypocket.dtos.UserDto;
import com.alkemy.paypocket.entities.Role;
import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.repositories.RoleRepository;
import com.alkemy.paypocket.repositories.UserRepository;
import com.alkemy.paypocket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "auth")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @PostMapping(path = "/register")
    public User registerUser(@RequestBody UserDto userDto){

        return userService.saveUser(userDto);
    }



}
