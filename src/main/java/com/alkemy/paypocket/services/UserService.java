package com.alkemy.paypocket.services;

import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.dtos.UserDto;
import com.alkemy.paypocket.mappers.UserMapper;
import com.alkemy.paypocket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setSoftDelete(true);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }

    }

    public User saveUser(UserDto userDto) {

        User newUser = userMapper.toUser(userDto);
        userRepository.save(newUser);

        return newUser;
    }
}