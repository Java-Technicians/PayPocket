package com.alkemy.paypocket.services;

import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.dtos.UserDto;
import com.alkemy.paypocket.mappers.UserMapper;
import com.alkemy.paypocket.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public Optional<User> findUser(Integer id){

        if (userRepository.existsById(id)){
            return userRepository.findById(id);
        }else {
            throw new RuntimeException("Usuario no encontrado");
        }

    }

    public User updateUser(UserDto userDto, Integer id){
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el ID: " + id));
        userToUpdate.setFirstName(userDto.getFirstName());
        userToUpdate.setLastName(userDto.getLastName());
        userToUpdate.setPasswords(userDto.getPasswords());
        userToUpdate.setEmail(userDto.getEmail());
        return userRepository.save(userToUpdate);
    }
}