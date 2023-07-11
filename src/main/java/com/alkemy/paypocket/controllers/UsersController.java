package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.dtos.UsersDTO;
import com.alkemy.paypocket.mappers.UsersMapper;
import com.alkemy.paypocket.repositories.UsersRepository;
import com.alkemy.paypocket.servicies.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersMapper mapper;

    @GetMapping(path ="/auth")
    public String auth(){
        return "auth";
    }

    @GetMapping(path ="/register")
    public UsersDTO register(){
        return new UsersDTO(register().getFirstName(), register().getLastName(), register().getEmail(), register().getPasswords());
    }

    @PostMapping(path ="/save")
    public void save(@Valid @RequestBody UsersDTO dto){
        usersService.save(mapper.toUsers(dto));
    }

}
