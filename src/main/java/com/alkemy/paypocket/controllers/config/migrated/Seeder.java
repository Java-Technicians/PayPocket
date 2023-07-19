package com.alkemy.paypocket.controllers.config.migrated;

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



    private void usuario(int id_rol) {
        String now = LocalDateTime.now().toString();
        String nombre   = Ramdomizer.nombre();
        String apellido = Ramdomizer.apellido();
        String email    = Ramdomizer.email(nombre, apellido);
        String password = Ramdomizer.password();

        String sql = "INSERT INTO user (creation_date, email, first_name, last_name, passwords, soft_delete, update_date, role_id) VALUES (";
        sql+="'"+now+"','"+email+"','"+nombre+"','"+apellido+"','"+password+"',"+1+",'"+now+"',"+id_rol+")";
        jdbcTemplate.update(sql);
    }


    private void rol(String name, String descripcion) {

        String now = LocalDateTime.now().toString();
        String values = "VALUES('" + name + "','" + descripcion + "','" + now + "','" + now + "')";
        String sql = "INSERT INTO roles (role_name, role_desc, creation_date, update_date) " + values;
      
        jdbcTemplate.update(sql);
    }
    
    public String seederRol() {
        try {
            this.rol("Administrador", "Rol de Administrador");
            this.rol("Regulares", "Rol Regular");

            return "MIGRACION SEEDER ROL OK :)";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String seederUsuarios() {
        try {

            for (int i = 0; i < 100; i++) {
                this.usuario(1);
            }
            for (int i = 0; i < 100; i++) {
                this.usuario(2);
            }

            return "MIGRACION SEEDER USUARIOS :)";
        } catch (Exception e) {
            return e.getMessage();
        }
    }




  


}
