package com.alkemy.paypocket.controller;

import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllListarlos() {
        return userService.findAll();
    }
}
