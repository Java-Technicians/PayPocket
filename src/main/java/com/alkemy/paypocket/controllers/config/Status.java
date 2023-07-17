package com.alkemy.paypocket.controllers.config;

import org.springframework.web.bind.annotation.*;


@RestController

public class Status {

    @GetMapping("OK")
    public String status(){
        return "OK";
    }

}
