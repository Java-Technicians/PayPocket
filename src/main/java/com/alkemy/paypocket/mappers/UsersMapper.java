
package com.alkemy.paypocket.mappers;


import com.alkemy.paypocket.dtos.UsersDTO;
import com.alkemy.paypocket.entities.Users;
import com.alkemy.paypocket.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {
    @Autowired
    UsersRepository usersRepository;

    public UsersDTO toUsersDTO(Users users){
        UsersDTO dto = new UsersDTO();
        dto.setFirstName(users.getFirstName());
        dto.setLastName(users.getLastName());
        dto.setPasswords(users.getPasswords());
        if(users.getEmail() != null){
            dto.setEmail(users.getEmail());
        }else{
            dto.setEmail("");
        }
        return dto;
    }

    public Users toUsers(UsersDTO dto){
        Users u = usersRepository.findByEmail(dto.getEmail());
        if (u == null) {  // Si no existe el usuario, lo creo
            u = new Users();
            u.setEmail(dto.getEmail());
            u.setFirstName(dto.getFirstName());
            u.setLastName(dto.getLastName());
            u.setPasswords(dto.getPasswords());
        }

        return u;
    }
}

