package com.alkemy.paypocket.dtos;

import com.alkemy.paypocket.entities.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoDto {

    @NotBlank(message = "Campo name OBLIGATORIO")
    private String name;

    @NotNull(message = "Campo amount OBLIGATORIO")
    private double amount;

    @NotNull(message = "Campo account_id OBLIGATORIO")
    private Integer account_id;

}
