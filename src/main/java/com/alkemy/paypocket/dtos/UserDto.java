package com.alkemy.paypocket.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

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
