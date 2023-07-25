package com.alkemy.paypocket.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.alkemy.paypocket.dtos.FixedTermDepositsDto;
import com.alkemy.paypocket.entities.Fixed_term_deposits;

import lombok.Getter;
import lombok.Setter;

@Service
public class FixedTermDepositsService {

    @Getter
    @Setter
    private class Response{

        String createDate;
        String closeDate;
        Double amount ;

        Double interest_again; // Rendimiento.
        Double new_amount;

    
    }


    /**
     * La logica realiza las validaciones antes de efectuar el calculo
     * 
     * 1- Las fechas deben tener un minimo de dia de hoy
     * 2- La fecha de cierre debe tener al menos 30 dias mas que la fecha de
     * creacion.
     * 
     * @param fixedDeposit
     * @return una respuesta con la simulacion.
     * @throws Exception errores de validaciones
     */

    public Object simulate(FixedTermDepositsDto fixedDeposit) throws Exception {

        LocalDate now = LocalDate.now();

        Fixed_term_deposits deposit = new Fixed_term_deposits();
        deposit.setAmount(fixedDeposit.getAmount());
        deposit.setClosingDate(fixedDeposit.getCloseDate());
        deposit.setCreationDate(fixedDeposit.getCreateDate());

        if (deposit.getClosingDate().isBefore(now) && deposit.getCreationDate().isBefore(now)) {
            throw new Exception("Las fechas deben ser superior al dia : " + now.toString());
        }

        if (!deposit.getClosingDate().isAfter(deposit.getCreationDate().plusDays(30))) {
            throw new Exception(
                    "La Fecha de Cierre deben debe tener un minimo de 30 dias superior a la fecha de inicio");
        }

        /*
         * Logica de simulacion.
         * 
         * INTEREST = Valor porcentual de interes por dia.
         * 
         */

        Double INTEREST = 0.5;
        Response response = new Response();

        response.setAmount(deposit.getAmount());
        response.setCreateDate(deposit.getCreationDate().toString());
        response.setCloseDate(deposit.getClosingDate().toString());


        // El valor absoluto de dias entre la fecha de inicio y fecha Final 2022/04/01 - 2022/04/31 = 30
        long dateDaysAbsolute = ChronoUnit.DAYS.between(deposit.getCreationDate(), deposit.getClosingDate());

        response.setInterest_again(INTEREST*dateDaysAbsolute); 
        response.setNew_amount(INTEREST * dateDaysAbsolute * deposit.getAmount());


        return response;


    }



}

