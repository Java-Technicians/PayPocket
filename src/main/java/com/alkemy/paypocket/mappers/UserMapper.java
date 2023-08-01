package com.alkemy.paypocket.mappers;

import com.alkemy.paypocket.dtos.UserDto;
import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.repositories.RoleRepository;
import com.alkemy.paypocket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public User toUser(UserDto userDto){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Correo registrado");
        }

        User user = new User();

        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setCreationDate(LocalDate.now());
        user.setLastName(userDto.getLastName());
        user.setPasswords(passwordEncoder.encode(userDto.getPasswords()));
        user.setSoftDelete(false);

        if (userDto.getRolName() == null){
            user.setRole(roleRepository.findByname("USER"));
        }

        return user;
    }

    public UserDto toDto (User user){

        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPasswords(user.getPasswords());
        userDto.setRolName(user.getRole().getName());

        return userDto;

    }

}
