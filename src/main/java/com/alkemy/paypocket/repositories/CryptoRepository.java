package com.alkemy.paypocket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.paypocket.entities.Crypto;


@Repository
public interface CryptoRepository extends JpaRepository<Crypto, Integer>{
    
}
