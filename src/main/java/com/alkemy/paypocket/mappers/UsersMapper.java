
package com.alkemy.paypocket.mappers;


import com.alkemy.paypocket.dtos.UsersDTO;
import com.alkemy.paypocket.entities.Users;
import com.alkemy.paypocket.servicies.UsersService;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {
    @Autowired
    UsersService usersService;
    


    public UsersDTO toUsersDTO(@NotNull Users users){
        UsersDTO dto = new UsersDTO();
        dto.setFirstName(users.getFirstName());
        dto.setLastName(users.getLastName());
        dto.setPassword(users.getPassword());
        if(users.getEmail() != null){
            dto.setEmail(users.getEmail());
        }else{
            dto.setEmail("");
        }
        return dto;
    }

    public Users toUsers(UsersDTO dto){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

        Users u = usersService.findByEmail(dto.getEmail());
        if (u == null) {  // Si no existe el usuario, lo creo
            u = new Users();
            u.setEmail(dto.getEmail());
            u.setFirstName(dto.getFirstName());
            u.setLastName(dto.getLastName());
            u.setPassword(passwordEncoder.encode(dto.getPassword()));

            u.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
            u.setUpdateDate(u.getCreationDate());
        }

        return u;
    }


 
}

