package com.alkemy.paypocket.services;

import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.dtos.UserDto;
import com.alkemy.paypocket.mappers.UserMapper;
import com.alkemy.paypocket.message.ResponseData;
import com.alkemy.paypocket.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<User> findAll(){
        return userRepository.findAll()
                .stream()
                .filter(User -> !User.getSoftDelete())
                .toList();
    }

    public Page<User> findAllByExample(Pageable pageable) {
        User user = new User();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<User> example = Example.of(user, matcher);
        return userRepository.findAll(example, pageable);
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

    public ResponseData<?> saveUser(UserDto userDto) {

        try{

            User newUser = userMapper.toUser(userDto);
            userRepository.save(newUser);

            UserDto newUserDto = userMapper.toDto(newUser);

            return new ResponseData<>(newUserDto,"Usuario creado");


        }catch (Exception e){
            return new ResponseData<>(null,e.getMessage());
        }

    }

    public ResponseData<UserDto> findUser(Integer id){

        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el ID: " + id));

        try {

            if (!user.getSoftDelete()){
                return new ResponseData<>(userMapper.toDto(user), "Usuario Encontrado") ;
            }else {
                throw new RuntimeException("Usuario ya Eliminado");
            }

        }catch (Exception e){
            return new ResponseData<>(null, e.getMessage());
        }

    }

    public ResponseData<UserDto> updateUser(UserDto userDto, Integer id){
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con el ID: " + id));

        try {

            if (!userToUpdate.getSoftDelete()){

                userToUpdate.setFirstName(userDto.getFirstName());
                userToUpdate.setLastName(userDto.getLastName());
                userToUpdate.setPasswords(userDto.getPasswords());
                userToUpdate.setEmail(userDto.getEmail());
                userRepository.save(userToUpdate);

                return new ResponseData<>(userMapper.toDto(userToUpdate), "Usuario Actualizado") ;
            }else {
                throw new RuntimeException("Usuario ya Eliminado");
            }

        }catch (Exception e){
            return new ResponseData<>(null, e.getMessage());
        }

    }


}