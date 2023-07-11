package com.alkemy.paypocket.servicies;

import com.alkemy.paypocket.entities.Users;
import com.alkemy.paypocket.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Users findByEmail(String email){
        return usersRepository.findByEmail(email);
    }
}
