package com.alkemy.paypocket.configurations;

import com.alkemy.paypocket.entities.Role;
import com.alkemy.paypocket.repositories.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class RoleConfiguration {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void initDefaultRoles(){

        if (roleRepository.count() == 0){

            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setDescripcion("ADMIN");
            adminRole.setCreationDate(LocalDate.now());
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("USER");
            userRole.setDescripcion("USER");
            userRole.setCreationDate(LocalDate.now());
            roleRepository.save(userRole);

        }

    }

}
