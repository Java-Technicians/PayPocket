package com.alkemy.paypocket.repositories;

import com.alkemy.paypocket.entities.Fixed_term_deposits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Fixed_term_depositsRepository extends JpaRepository<Fixed_term_deposits, Integer> {

    Optional<Fixed_term_deposits> findByAccount_Id(Integer id);

}
