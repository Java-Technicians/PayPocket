package com.alkemy.paypocket.controllers.config.migrated;

import java.security.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class Seeder {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Seeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    // private void usuario(int id_rol) {
    //     String sql = "INSERT INTO users (name) VALUES (?)";
    //     jdbcTemplate.update(sql, name);
    // }


    private void rol(String name, String descripcion) {
        String now = LocalDateTime.now().toString();
        String values = "VALUES('" + name + "','" + descripcion + "','" + now + "','" + now + "')";
        String sql = "INSERT INTO roles (role_name, role_desc, creation_date, update_date) " + values;
        jdbcTemplate.update(sql);
    }
    
    public String migrate() {
        try {
            this.rol("Administrador", "Rol de Administrador");
            return "MIGRACION SEEDER OK :)";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    


}
