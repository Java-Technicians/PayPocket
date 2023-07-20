package com.alkemy.paypocket.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alkemy.paypocket.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  Boolean existsByEmail (String email);
}
