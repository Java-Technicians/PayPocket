package com.alkemy.paypocket.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alkemy.paypocket.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    public Users findByEmail(String email);
}
