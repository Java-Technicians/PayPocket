package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.dtos.UsersDTO;
import com.alkemy.paypocket.entities.Users;
import com.alkemy.paypocket.mappers.UsersMapper;
import com.alkemy.paypocket.servicies.UsersService;
import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersMapper mapper;


    @PostMapping("auth/register")
    public ResponseEntity<Users> register(@Valid @RequestBody  UsersDTO dto) {

        Users user = mapper.toUsers(dto);
        Users respon = usersService.save(user);
        if(user!=null){
        }
        return ResponseEntity.created(URI.create("/users/" + respon.getId())).body(respon);
    }
    

    // @PostMapping(path ="/save")
    // public void save(@Valid @RequestBody UsersDTO dto){
    //     usersService.save(mapper.toUsers(dto));
    // }


}


