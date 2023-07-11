package com.alkemy.paypocket.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    @NotEmpty(message = "El campo no debe estar vacio")
    @NotNull
    @NotBlank(message = "El campo no puede estar en blanco")
    private String firstName;

    @NotEmpty(message = "El campo no debe estar vacio")
    @NotBlank(message = "El campo no puede estar en blanco")
    private String lastName;

    @NotEmpty(message = "El campo no debe estar vacío")
    @NotBlank(message = "El campo no puede estar en blanco")
    private String email;

    @NotEmpty(message = "El campo no debe estar vacío")
    @NotBlank(message = "El campo no puede estar en blanco")
    private String passwords;

}
