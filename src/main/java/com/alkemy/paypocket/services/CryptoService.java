package com.alkemy.paypocket.services;

import com.alkemy.paypocket.dtos.AccountDto;
import com.alkemy.paypocket.dtos.CryptoDto;
import com.alkemy.paypocket.entities.Account;
import com.alkemy.paypocket.entities.Crypto;
import com.alkemy.paypocket.mappers.AccountMapper;
import com.alkemy.paypocket.mappers.CryptoMapper;
import com.alkemy.paypocket.repositories.AccountRepository;
import com.alkemy.paypocket.repositories.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    @Autowired
    CryptoRepository cryptoRepository;

    @Autowired
    CryptoMapper cryptoMapper;

    public Crypto saveCrypto(CryptoDto cryptoDto)throws Exception {

        Crypto crypto = cryptoMapper.toCrypto(cryptoDto);
        cryptoRepository.save(crypto);

        return crypto;
    }

}
