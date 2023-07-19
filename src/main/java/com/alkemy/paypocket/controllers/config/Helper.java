package com.alkemy.paypocket.controllers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alkemy.paypocket.controllers.config.migrated.*;;

@RestController

/**
 * Controlador generico para ayudarnos a debbugear
 * 
 */

public class Helper {

    @Autowired
    Seeder seeder;


    /**
     * Controador para revisar si hicimos una conexion buena, deberia accederse como
     * localhost:8080/OK 
     * @return "OK :)"
     */
    @GetMapping("OK")
    public String status(){
        return "OK";
    }

    /**
     * 
     * Previamente ejecutar CREATE SCHEMA billetera_virtual
     * 
     * Controlador para hacer Seeder, dispara consultas de la clase
     * Seeder  SOLO DE DEBUG COMENTAR EN PRODUCCION
     */
    @GetMapping("seeder")
    public String seeder(){
        return this.seeder.migrate();
    }

}
