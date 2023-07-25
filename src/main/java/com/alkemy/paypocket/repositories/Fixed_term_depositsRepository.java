package com.alkemy.paypocket.repositories;

import com.alkemy.paypocket.entities.Fixed_term_deposits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fixed_term_depositsRepository extends JpaRepository<Fixed_term_deposits, Integer> {

}
