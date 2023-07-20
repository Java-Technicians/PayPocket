package com.alkemy.paypocket.repositories;

import com.alkemy.paypocket.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Boolean existsByCurrency (String currency);

    List<Account> findAllByUser_Id(Integer userId);
}