package com.alkemy.paypocket.controller;

import com.alkemy.paypocket.entities.Role;
import com.alkemy.paypocket.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping(path = "/hola")
    public String hola(){
        return "HOLLLLLLLLLLLLAAAAAAAAA | ";
    }

    @GetMapping(path = "/chau")
    public String chaucha(){
        return "CHAUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU";
    }
}
