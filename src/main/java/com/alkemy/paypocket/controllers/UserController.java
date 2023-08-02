package com.alkemy.paypocket.controllers;

import com.alkemy.paypocket.entities.User;
import com.alkemy.paypocket.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.alkemy.paypocket.dtos.UserDto;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "auth")
@Tag(name = "Usuario", description = "Controlador del usuario.")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar", description = "Lista los usuarios.")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> usersPage = userService.findAllByPagination(pageRequest);

        List<User> usersList = usersPage.getContent();

        return ResponseEntity.ok(usersList);
    }

    @DeleteMapping(path = "user/{user_id}")
    @Operation(summary = "Eliminar", description = "Elimina un usuario que este pasado por id.")
    public ResponseEntity<?> deleteUser(@PathVariable("user_id") Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();

    }

    @PostMapping(path = "/register")
    @Operation(summary = "Agregar", description = "Agrega un usuario.")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto userDto, BindingResult result) {

        if (result.hasErrors()) {
            List<String> erros = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(erros);
        }

        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @GetMapping(path = "/user/{user_id}")
    @Operation(summary = "Obtener", description = "Obtiene un usuario por medio del id de cuenta.")
    public ResponseEntity<?> getUser(@PathVariable("user_id") Integer id) {
        return ResponseEntity.ok(userService.findUser(id));
    }

    @PatchMapping(path = "/user/{user_id}")
    @Operation(summary = "Editar", description = "Edita un usuario.")
    public ResponseEntity<?> updateUser(@PathVariable("user_id") Integer user_id, @RequestBody @Valid UserDto userDto,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(erros);
        }

        return ResponseEntity.ok(userService.updateUser(userDto, user_id));
    }

}
