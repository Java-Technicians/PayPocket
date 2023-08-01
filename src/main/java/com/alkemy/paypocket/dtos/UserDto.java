package com.alkemy.paypocket.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Usuario DTO", description = "DTO del usuario")
public class UserDto {

    @Schema(description = "Nombre del usuario", example = "Pedro")
    @NotBlank(message = "Campo firstName OBLIGATORIO ")
    private String firstName;

    @NotBlank(message = "Campo lastName OBLIGATORIO ")
    private String lastName;

    @NotBlank(message = "Campo email OBLIGATORIO ")
    private String email;

    @NotBlank(message = "Campo passwords OBLIGATORIO ")
    private String passwords;

    private String rolName;

}
