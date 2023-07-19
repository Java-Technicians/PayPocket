package com.alkemy.paypocket.services;

import com.alkemy.paypocket.dtos.UserDto;
import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.mappers.UserMapper;
import com.alkemy.paypocket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

   public User saveUser(UserDto userDto){

       User newUser = userMapper.toUser(userDto);
        userRepository.save(newUser);

        return newUser;
    }


}
