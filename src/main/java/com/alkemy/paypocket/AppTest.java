package com.alkemy.paypocket;

import com.alkemy.paypocket.entities.Role;
import com.alkemy.paypocket.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppTest implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {

        System.out.println("Mensaje antes de que inicie springboot");
        SpringApplication.run(AppTest.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Mensaje dentro de springboot");
        Role pepe = new Role();
        pepe.setName("ADMIN");
        pepe.setDescripcion("soy una description");
        roleRepository.save(pepe);
    }
}
