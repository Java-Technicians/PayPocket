package com.alkemy.paypocket.repositories;

import com.alkemy.paypocket.entities.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Boolean existsByCurrency (String currency);

    List<Account> findAllByUser_Id(Integer userId);

    Boolean existsByUserIdAndCurrency (Integer userID, String currency);
}