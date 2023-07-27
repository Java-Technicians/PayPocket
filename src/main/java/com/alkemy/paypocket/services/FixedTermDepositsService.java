package com.alkemy.paypocket.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.paypocket.dtos.FixedTermDepositsDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.Fixed_term_deposits;
import com.alkemy.paypocket.mappers.FixedTermDepositsMapper;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.Fixed_term_depositsRepository;

import lombok.*;

@Service
public class FixedTermDepositsService {

    static final Double INTEREST = 0.5;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    Fixed_term_depositsRepository fixedDepositRepository;

    @Autowired
    FixedTermDepositsMapper fixedMapper;

    @Getter
    @Setter
    private class Response {

        String createDate;
        String closeDate;
        Double amount;

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

        Fixed_term_deposits deposit = new Fixed_term_deposits();
        deposit.setAmount(fixedDeposit.getAmount());
        deposit.setClosingDate(fixedDeposit.getCloseDate());
        deposit.setCreationDate(fixedDeposit.getCreateDate());

        this.validate(deposit);

        /*
         * Logica de simulacion.
         * 
         * INTEREST = Valor porcentual de interes por dia.
         * 
         */

        Response response = new Response();

        response.setAmount(deposit.getAmount());
        response.setCreateDate(deposit.getCreationDate().toString());
        response.setCloseDate(deposit.getClosingDate().toString());

        // El valor absoluto de dias entre la fecha de inicio y fecha Final 2022/04/01 -
        // 2022/04/31 = 30

        long dateDaysAbsolute = ChronoUnit.DAYS.between(deposit.getCreationDate(), deposit.getClosingDate());
        Double interest_again = INTEREST * dateDaysAbsolute;

        response.setInterest_again(interest_again);
        Double increment_amount = (interest_again / 100) + 1;
        response.setNew_amount(increment_amount * deposit.getAmount());

        return response;

    }

    public Fixed_term_deposits saveTermDeposit(FixedTermDepositsDto termDeposit) throws Exception {

        Fixed_term_deposits deposit = fixedMapper.toFixedDeposit(termDeposit);

        this.validate(deposit);

        if(termDeposit.getAccount_id()==null){
            throw new Exception("No se ingreso un account_id de Cuenta");
        }

        Optional<Account> optionalAccount = accountRepository.findById(termDeposit.getAccount_id());
        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();

            if (existingAccount.getBalance() >= deposit.getAmount()) {
                /*
                 * Proceso de guardado de plazo fijo.
                 */
                long dateDaysAbsolute = ChronoUnit.DAYS.between(deposit.getCreationDate(), deposit.getClosingDate());
                
                deposit.setInterest(INTEREST * dateDaysAbsolute);
                existingAccount.setBalance(existingAccount.getBalance() - deposit.getAmount());
                
                accountRepository.save(existingAccount);
                deposit.setAccount(existingAccount);
                
                fixedDepositRepository.save(deposit);

            } else {
                throw new Exception("Cuenta de cliente no tiene saldo suficiente");
            }

        } else {
            throw new Exception("Cuenta de cliente no existente");
        }

        return deposit;
    }


    private void validate(Fixed_term_deposits deposit) throws Exception {

        LocalDate now = LocalDate.now();

        if (deposit.getClosingDate().isBefore(now) && deposit.getCreationDate().isBefore(now)) {
            throw new Exception("Las fechas deben ser superior al dia : " + now.toString());
        }

        if (!deposit.getClosingDate().isAfter(deposit.getCreationDate().plusDays(30))) {
            throw new Exception(
                    "La Fecha de Cierre deben debe tener un minimo de 30 dias superior a la fecha de inicio");
        }

    }

}
