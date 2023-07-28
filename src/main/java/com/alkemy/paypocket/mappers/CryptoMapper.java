package com.alkemy.paypocket.mappers;

import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.dtos.CryptoDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.Crypto;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class CryptoMapper {

    @Autowired
    CryptoRepository cryptoRepository;

    @Autowired
    AccountRepository accountRepository;

    public Crypto toCrypto(CryptoDto cryptoDto) throws Exception {

        Crypto crypto = new Crypto();

        crypto.setName(cryptoDto.getName());
        crypto.setAmount(cryptoDto.getAmount());

        Integer accountId = cryptoDto.getAccount_id();
        Optional<Account> account = accountRepository.findById(accountId);

        if(!account.isPresent() || account.get().isSoftDelete()){
            throw  new IllegalArgumentException("Usuario con ID " + accountId + " no encontrado.");
        }

        crypto.setAccount(account.get());

        crypto.setCreationDate(LocalDate.now());
        crypto.setClosingDate(LocalDate.now());
        crypto.setSoftDelete(false);

        return crypto;
    }

}
